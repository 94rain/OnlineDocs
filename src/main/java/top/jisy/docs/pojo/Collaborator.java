package top.jisy.docs.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Collaborator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_USERS")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_DOCS")
    @JsonIgnore
    private Doc doc;

    @Enumerated(EnumType.STRING)
    @Column(name = "HAS_ACCESS")
    private HasAccess hasAccess;

    @Column(name = "CTIME")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime ctime;

    @PrePersist
    private void onInsert() {
        this.ctime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public HasAccess hasAccess() {
        return hasAccess;
    }

    public void setHasAccess(HasAccess hasAccess) {
        this.hasAccess = hasAccess;
    }

    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ctime == null) ? 0 : ctime.hashCode());
        result = prime * result + ((doc == null) ? 0 : doc.hashCode());
        result = prime * result + ((hasAccess == null) ? 0 : hasAccess.hashCode());
        result = prime * result + id;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Collaborator other = (Collaborator) obj;
        if (ctime == null) {
            if (other.ctime != null) {
                return false;
            }
        } else if (!ctime.equals(other.ctime)) {
            return false;
        }
        if (doc == null) {
            if (other.doc != null) {
                return false;
            }
        } else if (!doc.equals(other.doc)) {
            return false;
        }
        if (hasAccess != other.hasAccess) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!user.equals(other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("User: \n")
                .append("\tid: " + this.id + "\n")
                .append("\tUser: " + this.user.getName() + "\n")
                .append("\tDocument: " + this.doc.getId() + "\n")
                .append("\tHas access: " + this.hasAccess.getHasAccessString() + "\n")
                .append("\tCreated: " + this.ctime + "\n")
                .toString();
    }
}