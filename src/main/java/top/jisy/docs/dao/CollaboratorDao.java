package top.jisy.docs.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import top.jisy.docs.enums.HasAccess;
import top.jisy.docs.pojo.Collaborator;
import top.jisy.docs.pojo.Doc;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

// @todo update to Mybatis
@Component
public class CollaboratorDao {
    private static final Logger log = LoggerFactory.getLogger(CollaboratorDao.class);
    @PersistenceContext(unitName = "cmd")
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional
    public List<Collaborator> getCollaboratorsForDoc(Doc d) {

        List<Collaborator> collaborators = new ArrayList<>();

        try {
            collaborators = (List<Collaborator>) this.em
                    .createQuery("SELECT c FROM Collaborator c WHERE c.doc.id=:id AND c.hasAccess=:hasAccess")
                    .setParameter("id", d.getId())
                    .setParameter("hasAccess", HasAccess.Y)
                    .getResultList();
        } catch (NoResultException e) {
            log.error(e.getLocalizedMessage(), e);
            return null;
        }

        return collaborators;
    }
}
