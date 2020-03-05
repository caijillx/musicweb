package com.group9.musicweb.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String content;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date creatTime;

    @ManyToOne
    private User user;
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", creatTime=" + creatTime +
                '}';
    }

    public Comment() {}

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

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}
