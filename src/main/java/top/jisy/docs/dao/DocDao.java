package top.jisy.docs.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import top.jisy.docs.pojo.Doc;
import top.jisy.docs.pojo.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


// @todo update to Mybatis
@SuppressWarnings("unchecked")
@Transactional
@Component
public class DocDao {
    @PersistenceContext(unitName = "cmd")
    private EntityManager em;
    public List<Doc> getDocsOwnedBy(User u) {
        List<Doc> docs = new ArrayList<>();

        try {
            docs = (List<Doc>) this.em
                    .createQuery("SELECT d FROM Doc d WHERE d.repo.owner.id=:user_id ORDER BY d.ctime DESC")
                    .setParameter("user_id", u.getId())
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }

        return docs;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<Doc> getDocsCollaboratedBy(User u) {
        List<Doc> docs = new ArrayList<>();

        try {
            docs = (List<Doc>) this.em
                    .createQuery("SELECT d FROM Doc d, Collaborator c WHERE d.id = c.doc.id AND c.user.id = :userId ORDER BY d.ctime DESC")
                    .setParameter("userId", u.getId())
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }

        return docs;
    }
}