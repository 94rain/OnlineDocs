package top.jisy.docs.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "HASH")
    private String hash;

    @Column(name = "CTIME")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime ctime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_DOCS")
    private Doc doc;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((ctime == null) ? 0 : ctime.hashCode());
        result = prime * result + ((doc == null) ? 0 : doc.hashCode());
        result = prime * result + ((hash == null) ? 0 : hash.hashCode());
        result = prime * result + id;
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

        History other = (History) obj;
        if (content == null) {
            if (other.content != null) {
                return false;
            }
        } else if (!content.equals(other.content)) {
            return false;
        }
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
        if (hash == null) {
            if (other.hash != null) {
                return false;
            }
        } else if (!hash.equals(other.hash))
            return false;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("History: \n")
                .append("\tid: " + this.id + "\n")
                .append("\tHash: " + this.hash + "\n")
                .append("\tCreated: " + this.ctime + "\n")
                .append("\tDocument: " + this.doc.getId() + "\n")
                .append("\tContent: " + this.content + "\n")
                .toString();
    }
}