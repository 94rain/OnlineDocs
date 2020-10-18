package top.jisy.docs.pojo;

import java.util.Date;

public class Collaborator {
    private Integer id;

    private Integer fkUser;

    private Integer fkDoc;

    private String hasAccess;

    private Date ctime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }

    public Integer getFkDoc() {
        return fkDoc;
    }

    public void setFkDoc(Integer fkDoc) {
        this.fkDoc = fkDoc;
    }

    public String getHasAccess() {
        return hasAccess;
    }

    public void setHasAccess(String hasAccess) {
        this.hasAccess = hasAccess == null ? null : hasAccess.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}