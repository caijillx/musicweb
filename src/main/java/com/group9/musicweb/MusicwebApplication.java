package com.group9.musicweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ComponentScan("com")
@EntityScan("com.group9.musicweb.entity")
@EnableJpaAuditing
public class MusicwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicwebApplication.class, args);
    }

}
