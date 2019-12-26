package com.group9.musicweb.entity;
import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tagName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date addtime;

}
