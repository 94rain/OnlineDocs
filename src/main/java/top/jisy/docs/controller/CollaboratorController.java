package top.jisy.docs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jisy.docs.constant.ResponseParameters;
import top.jisy.docs.dao.DocDao;
import top.jisy.docs.dao.mapper.UserMapper;
import top.jisy.docs.entity.ResponseObject;
import top.jisy.docs.pojo.Collaborator;
import top.jisy.docs.pojo.User;
import top.jisy.docs.service.CollaboratorService;
import top.jisy.docs.service.UserService;
import top.jisy.docs.util.SessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;


// @todo move DAO actions to service class
@RestController
@RequestMapping("/api/collaborator")
public class CollaboratorController {

    private static Logger log = LoggerFactory.getLogger(CollaboratorController.class);

    @Autowired
    SessionUtils sessionUtils;

    @Autowired
    CollaboratorService collaboratorService;

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseObject addCollaborator(HttpServletRequest request) {
        if (!sessionUtils.isLoggedIn()) {
            return ResponseObject.fail(Response.Status.BAD_REQUEST, ResponseParameters.ADD_COLLABORATOR_NOT_LOGGED_IN);
        }

        String documentId = request.getParameter("documentId");
        String collaboratorUsername = request.getParameter("collaboratorUsername");

        User user = userService.getUserByName(collaboratorUsername);

        Collaborator newCollaborator = new Collaborator();
        newCollaborator.setFkDoc(Integer.parseInt(documentId));
        newCollaborator.setFkUser(user.getId());
        newCollaborator.setHasAccess("Y");

        collaboratorService.createCollaborator(newCollaborator);
        log.info("{}: Created collaborator '{}' for document '{}'", request.getRequestURI(), collaboratorUsername, documentId);

        //@todo parameters
        // return new Success("The collaborator '%s' was successfully added to your document.", collaboratorUsername);
        return ResponseObject.success(ResponseParameters.COLLABORATOR_ADDED);
    }

    @GetMapping("/remove")
    public ResponseObject removeCollaborator(HttpServletRequest request) {
        if (!sessionUtils.isLoggedIn()) {
            return ResponseObject.fail(Response.Status.BAD_REQUEST, ResponseParameters.REMOVE_COLLABORATOR_NOT_LOGGED_IN);
        }

        String collaboratorId = request.getParameter("collaboratorId");
        Collaborator collaborator = collaboratorService.getCollaborator(Integer.parseInt(collaboratorId));

        collaboratorService.removeCollaborator(collaborator);
        log.info("{}: Removed collaborator '{}' from document '{}'", request.getRequestURI(), collaborator.getFkUser(), collaborator.getFkDoc());

        // return new Success("The collaborator '%s' was successfully removed from your document.", collaborator.getUser().getName());
        return ResponseObject.success(ResponseParameters.COLLABORATOR_REMOVED);
    }

}
