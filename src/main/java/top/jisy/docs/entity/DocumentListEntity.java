package top.jisy.docs.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import top.jisy.docs.pojo.Collaborator;
import top.jisy.docs.pojo.Doc;

import java.util.List;

public class DocumentListEntity {

    private final String icon;

    private final Doc document;

    private final List<Collaborator> collaborators;

    public DocumentListEntity(String icon, Doc document, List<Collaborator> collaborators) {
        this.icon = icon;
        this.document = document;
        this.collaborators = collaborators;
    }

    public String getIcon() {
        return icon;
    }

    public Doc getDocument() {
        return document;
    }

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }
}
