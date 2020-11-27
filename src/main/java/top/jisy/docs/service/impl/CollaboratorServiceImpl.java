package top.jisy.docs.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import top.jisy.docs.dao.CollaboratorMapper;
import top.jisy.docs.pojo.Collaborator;
import top.jisy.docs.pojo.Doc;
import top.jisy.docs.pojo.User;
import top.jisy.docs.service.CollaboratorService;

import java.util.List;

public class CollaboratorServiceImpl implements CollaboratorService {

    private static final Logger log = LoggerFactory.getLogger(CollaboratorService.class);
    @Autowired
    CollaboratorMapper collaboratorMapper;

    @Override
    public void createCollaborator(Collaborator c) {
        collaboratorMapper.insertSelective(c);
        log.info("createCollaborator success");
    }

    @Override
    public List<Collaborator> getCollaboratorsForDoc(Doc d) {
        return collaboratorMapper.getCollaboratorsForDoc(d.getId(), "Y");
    }

    @Override
    public List<Collaborator> getCollaborationsForUser(User u) {
        return null;
    }

    @Override
    public Collaborator getCollaborator(User u, Doc d) {
        return null;
    }

    @Override
    public Collaborator getCollaborator(int id) {
        return null;
    }

    @Override
    public int updateCollaborator(Collaborator c) {
        return 0;
    }

    @Override
    public int removeCollaborator(Collaborator c) {
        return 0;
    }
}
