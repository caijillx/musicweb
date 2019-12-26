package com.group9.musicweb.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "music")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String resaddr;
    private String info;
    private String zuozhe;
    private String mvresaddr;

}
