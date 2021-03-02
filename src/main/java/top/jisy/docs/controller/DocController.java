package top.jisy.docs.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.jisy.docs.constant.ResponseParameters;
import top.jisy.docs.dao.CollaboratorDao;
import top.jisy.docs.dao.DocDao;
import top.jisy.docs.entity.DocumentListEntity;
import top.jisy.docs.entity.ResponseObject;
import top.jisy.docs.pojo.Collaborator;
import top.jisy.docs.pojo.Doc;
import top.jisy.docs.pojo.User;
import top.jisy.docs.service.DocService;
import top.jisy.docs.util.SessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/document")
// @todo cleanup Dao actions to single service class
public class DocController {
    private static final Logger log = LoggerFactory.getLogger(DocController.class);

    @Autowired
    DocService docService;

    @Autowired
    DocDao docDao;

    @Autowired
    CollaboratorDao collaboratorDao;

    @Autowired
    SessionUtils sessionUtils;

    @GetMapping("/all")
    public ResponseObject getAllDocsByUser() {
        if (!sessionUtils.isLoggedIn()) {
            return ResponseObject.fail(Response.Status.BAD_REQUEST, ResponseParameters.CREATE_DOCUMENT_NOT_LOGGED_IN);
        }
        // int userId = sessionUtils.getUser().getId();
        User user = sessionUtils.getUser();
        List<Doc> ownedDocuments = docDao.getDocsOwnedBy(user);
        List<Doc> collaboratorDocuments = docDao.getDocsCollaboratedBy(user);

        List<DocumentListEntity> documentEntities = new ArrayList<>();

        if (ownedDocuments != null) {
            String listIcon;
            for (Doc document : ownedDocuments) {
                List<Collaborator> collaborators = collaboratorDao.getCollaboratorsForDoc(document);

                if (collaborators != null && !collaborators.isEmpty()) {
                    listIcon = "group";
                } else {
                    listIcon = "person";
                }

                documentEntities.add(new DocumentListEntity(listIcon, document, collaborators));
            }
        }

        if (collaboratorDocuments != null) {
            for (Doc collaboratedDocument : collaboratorDocuments) {
                List<Collaborator> collaborators = collaboratorDao.getCollaboratorsForDoc(collaboratedDocument);

                documentEntities.add(new DocumentListEntity("group", collaboratedDocument, collaborators));
            }
        }

        // DocumentListEntity documentListEntity = new DocumentListEntity("person",docService.getDocsByUserId(userId),new ArrayList<Collaborator>());
        return ResponseObject.success(documentEntities);
    }

    @GetMapping("/get/{UserId}")
    public List<Doc> getDocByUserId(@PathVariable("UserId") int id) {
        return docService.getDocsByUserId(id);
    }

    @PostMapping("/add")
    public ResponseObject addDocument(HttpServletRequest request, @RequestBody JSONObject json) {
        try {
            User currentUser = sessionUtils.getUser();
            int userId = currentUser.getId();
            String documentName = json.getString("documentName");
            docService.createDoc(userId, documentName);
            log.info("{}: Document '{}' was created for user '{}'", request.getRequestURI(), documentName, currentUser.getName());

            return ResponseObject.success(ResponseParameters.DOCUMENT_CREATED);
        } catch (Exception e) {
            log.error("Exception in addDocument: ", e);
            if (!sessionUtils.isLoggedIn()) {
                return ResponseObject.fail(Response.Status.BAD_REQUEST, ResponseParameters.CREATE_DOCUMENT_NOT_LOGGED_IN);
            }
            return ResponseObject.fail(Response.Status.INTERNAL_SERVER_ERROR, e.toString());
        }

    }
}
