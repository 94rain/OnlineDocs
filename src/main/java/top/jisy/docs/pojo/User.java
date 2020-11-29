package top.jisy.docs.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String name;

    private String password;

    private String mail;

    private Date ctime;

    private Date utime;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
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

    @Override
    public String toString() {
        return new StringBuilder()
                .append("User: ")
                .append("\tid: " + this.id + ",")
                .append("\tName: " + this.name + ",")
                .append("\tHash: " + this.password + ",")
                .append("\tMail: " + this.mail + ",")
                .append("\tCreated: " + this.ctime + ",")
                .append("\tLast updated: " + this.utime)
                .toString();
    }
}