package top.jisy.docs.pojo;

import java.util.Date;

public class History {
    private Integer id;

    private String hash;

    private Date ctime;

    private Integer fkDoc;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash == null ? null : hash.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getFkDoc() {
        return fkDoc;
    }

    public void setFkDoc(Integer fkDoc) {
        this.fkDoc = fkDoc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}