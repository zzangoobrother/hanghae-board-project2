package com.sparta.hanghaeboardproject2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class HanghaeBoardProject2Application {

    public static void main(String[] args) {
        SpringApplication.run(HanghaeBoardProject2Application.class, args);
    }

}
