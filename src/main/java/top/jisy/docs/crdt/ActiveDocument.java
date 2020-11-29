package top.jisy.docs.crdt;

import org.springframework.stereotype.Component;
import top.jisy.docs.pojo.Doc;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActiveDocument {

    private static final int QUEUE_LIMIT = 10;

    private Doc doc;
    private int state;
    private List<Session> users;
    private List<Message> latestTransforms;

    public ActiveDocument() {
        this.state = 0;
        this.users = new ArrayList<>();
        this.latestTransforms = new ArrayList<>();
    }

    public ActiveDocument(Doc doc, int state) {
        this.doc = doc;
        this.state = state;
        this.users = new ArrayList<>();
        this.latestTransforms = new ArrayList<>();
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Session> getUsers() {
        return users;
    }

    public void setUsers(List<Session> users) {
        this.users = users;
    }

    public List<Message> getLatestTransforms() {
        return latestTransforms;
    }

    public void setLatestTransforms(List<Message> latestTransforms) {
        this.latestTransforms = latestTransforms;
    }

    /**
     * Inserts a message from a user at a given index
     *
     * @param msg Given message
     */
    public void insert(Message msg) {
        this.doc.setContent(new StringBuilder()
                .append(doc.getContent(), 0, msg.getCursorPos())
                .append(msg.getMsg())
                .append(doc.getContent().substring(msg.getCursorPos()))
                .toString());
        this.state++;
        appendTransform(msg);
    }

    /**
     * Deletes a message from a user of a given length at a given index
     *
     * @param msg Given message
     */
    public void delete(Message msg) {
        this.doc.setContent(new StringBuilder()
                .append(doc.getContent(), 0, msg.getCursorPos())
                .append(doc.getContent().substring(msg.getCursorPos() + msg.getMsg().length()))
                .toString());
        this.state++;
        appendTransform(msg);
    }

    /**
     * Performs the lost operations on a given message
     *
     * @param msg      Given message
     * @param docState Given doc state
     */
    public void makeConsistent(Message msg, int docState) {
        int delta = QUEUE_LIMIT - (msg.getDocState() - docState);
        int cursorPos = msg.getCursorPos();

        for (int i = delta; i < QUEUE_LIMIT; i++) {

            Message queuedMsg = this.latestTransforms.get(i);

            if (msg.getCursorPos() > queuedMsg.getCursorPos()) {
                switch (queuedMsg.getMessageType()) {
                    case Insert:
                        cursorPos += queuedMsg.getMsg().length();
                        break;
                    case Delete:
                        cursorPos -= queuedMsg.getMsg().length();
                        break;
                    default:
                        throw new RuntimeException("Invalid MessageType used for normal editor messages");
                }
            }
        }
        msg.setCursorPos(cursorPos);
    }

    /**
     * Add a new transform to the list of applied transforms in the active doc
     *
     * @param msg Given message to append
     */
    private void appendTransform(Message msg) {
        msg.setDocState(msg.getDocState() + 1);

        if (this.latestTransforms.isEmpty()) {
            this.latestTransforms.add(msg);
        } else {
            if (this.latestTransforms.size() == QUEUE_LIMIT) {
                this.latestTransforms.remove(0);
            }

            this.latestTransforms.add(msg);
        }
    }
}
