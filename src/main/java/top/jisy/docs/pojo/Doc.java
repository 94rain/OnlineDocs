package top.jisy.docs.pojo;

import java.util.Date;

public class Doc {
    private Integer id;

    private String name;

    private Date ctime;

    private Date utime;

    private Integer cuser;

    private Integer uuser;

    private Integer fkRepo;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Integer getCuser() {
        return cuser;
    }

    public void setCuser(Integer cuser) {
        this.cuser = cuser;
    }

    public Integer getUuser() {
        return uuser;
    }

    public void setUuser(Integer uuser) {
        this.uuser = uuser;
    }

    public Integer getFkRepo() {
        return fkRepo;
    }

    public void setFkRepo(Integer fkRepo) {
        this.fkRepo = fkRepo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}