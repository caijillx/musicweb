package com.group9.musicweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
@EntityScan("com.group9.musicweb.entity")
public class MusicwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicwebApplication.class, args);
    }

}
