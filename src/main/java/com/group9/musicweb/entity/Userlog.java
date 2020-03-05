package com.group9.musicweb.entity;


import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userlog",catalog = "music")
public class Userlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    private String ip;
    @Temporal(TemporalType.TIMESTAMP)
    private Date add_time;

    public Userlog(){}

    @Override
    public String toString() {
        return "Userlog{" +
                "id=" + id +
                ", user=" + user +
                ", ip='" + ip + '\'' +
                ", add_time=" + add_time +
                '}';
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }
}
