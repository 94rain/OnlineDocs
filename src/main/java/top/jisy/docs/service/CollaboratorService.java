package top.jisy.docs.service;

import org.springframework.stereotype.Component;
import top.jisy.docs.pojo.Collaborator;
import top.jisy.docs.pojo.Doc;
import top.jisy.docs.pojo.User;

import java.util.List;

@Component
public interface CollaboratorService {
    /**
     * Add an entry to the doc table
     *
     * @param c Given doc object
     */
    void createCollaborator(Collaborator c);

    List<Collaborator> getCollaboratorsForDoc(Doc d);

    // List<Collaborator> getCollaborationsForUser(User u);
    //
    // Collaborator getCollaborator(User u, Doc d);

    Collaborator getCollaborator(int id);

    int updateCollaborator(Collaborator c);

    int removeCollaborator(Collaborator c);


}
