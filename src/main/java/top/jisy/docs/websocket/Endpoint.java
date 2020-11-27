package top.jisy.docs.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import top.jisy.docs.config.Hashing;
import top.jisy.docs.constant.FieldValues;
import top.jisy.docs.crdt.ActiveDocument;
import top.jisy.docs.crdt.Message;
import top.jisy.docs.crdt.MessageBroker;
import top.jisy.docs.dao.DocMapper;
import top.jisy.docs.dao.HistoryMapper;
import top.jisy.docs.dao.UserMapper;
import top.jisy.docs.enums.MessageType;
import top.jisy.docs.pojo.Doc;
import top.jisy.docs.pojo.History;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value = "/ws/{docId}/{username}/{userId}", encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class Endpoint {

    private static final Logger log = LoggerFactory.getLogger(Endpoint.class);

    /**
     * Active docs being worked on by n users
     */
    private static Map<Integer, ActiveDocument> docs = Collections.synchronizedMap(new HashMap<>());

    @Autowired
    private DocMapper docDao;

    @Autowired
    private UserMapper userDao;

    @Autowired
    private HistoryMapper historyDao;

    @Autowired
    private MessageBroker messageBroker;

    @Autowired
    private Hashing hashing;

    /**
     * Gets triggered once a web socket connection is opened.
     *
     * @param docId    Given document id
     * @param username Given user name of current user
     * @param session  Current user session
     */
    @OnOpen
    public void onOpen(@PathParam("docId") int docId, @PathParam(FieldValues.SESSION_USERNAME) String username, @PathParam(FieldValues.SESSION_USERID) int userId, Session session) {
        session.getUserProperties().put(FieldValues.SESSION_USERNAME, username);
        session.getUserProperties().put(FieldValues.SESSION_USERID, userId);

        Doc doc = null;

        if (docs.get(docId) == null) {
            doc = docDao.selectByPrimaryKey(docId);
            if (doc == null) {
                Message wrongDocIdMsg = messageBroker.createSystemMessage(userId, docId, -1, String.valueOf(docId), MessageType.WrongDocId);
                messageBroker.publishToSingleUser(wrongDocIdMsg, session);
                return;
            }
            docs.put(docId, new ActiveDocument(doc, 0));
        }

        if (doc == null) {
            doc = docs.get(docId).getDoc();
        }

        if (doc.getContent() == null) {
            docs.get(docId).getDoc().setContent("");
        }

        docs.get(docId).getUsers().add(session);

        Message contentInitMsg = messageBroker.createSystemMessage(userId, docId, docs.get(docId).getState(), doc.getContent(), MessageType.ContentInit);
        messageBroker.publishToSingleUser(contentInitMsg, session);

        Message documentTitleMsg = messageBroker.createSystemMessage(userId, docId, -1, doc.getName(), MessageType.DocumentTitle);
        messageBroker.publishToSingleUser(documentTitleMsg, session);

        Message userInitMsg = messageBroker.createSystemMessage(userId, docId, -1, messageBroker.getActiveUsers(docs.get(docId).getUsers(), session), MessageType.UsersInit);
        messageBroker.publishToSingleUser(userInitMsg, session);

        Message userJoinedMsg = messageBroker.createSystemMessage(userId, docId, -1, messageBroker.formatUserMessage(session), MessageType.UserJoined);
        messageBroker.publishToOtherUsers(userJoinedMsg, docs.get(docId), session);
    }

    /**
     * Gets triggered once a message is sent from the client.<br>
     *
     * @param msg     Given message
     * @param session Current user session
     */
    @OnMessage
    public void onMessage(Message msg, Session session) {
        ActiveDocument currentDoc = docs.get(msg.getDocId());

        messageBroker.transform(msg, currentDoc);
        messageBroker.publishToOtherUsers(msg, currentDoc, session);
    }

    /**
     * Gets triggered once a user closes the connection by logging off or closing the browser tab etc.
     * If it is the last user in the doc, the current state of the doc gets saved into the database.
     *
     * @param session Current user session
     */
    @OnClose
    public void onClose(Session session) {
        for (int docId : docs.keySet()) {
            for (Session singleUserSession : docs.get(docId).getUsers()) {
                if (singleUserSession.equals(session)) {

                    docs.get(docId).getUsers().remove(singleUserSession);

                    int userId = (int) singleUserSession.getUserProperties().get(FieldValues.SESSION_USERID);

                    Message userLeftMsg = messageBroker.createSystemMessage(userId, docId, -1, messageBroker.formatUserMessage(singleUserSession), MessageType.UserLeft);
                    messageBroker.publishToOtherUsers(userLeftMsg, docs.get(docId), session);

                    break;
                }
            }

            if (docs.get(docId).getUsers().isEmpty()) {

                // Save current doc from db to history
                Doc currentDocState = docDao.selectByPrimaryKey(docId);
                History history = new History();
                history.setContent(currentDocState.getContent());
                history.setDoc(currentDocState);
                history.setHash(hashing.hashDocContent(history.getContent()));
                historyDao.insertSelective(history);

                // Save current doc from client to db
                Doc activeDoc = docs.get(docId).getDoc();
                activeDoc.setUuser(userDao.getUserByName(session.getUserProperties().get(FieldValues.SESSION_USERNAME).toString().toLowerCase()));
                docDao.updateByPrimaryKeySelective(activeDoc);

                docs.remove(docId);
            }
        }
    }

    /**
     * Gets triggered on an error.
     *
     * @param t Given Exception
     */
    @OnError
    public void onError(Throwable t) {
        log.error(t.getLocalizedMessage(), t);
    }
}
