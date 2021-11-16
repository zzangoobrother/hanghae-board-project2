package com.sparta.hanghaeboardproject2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class HanghaeBoardProject2Application {

    private static final String APPLICATION_LOCATIONS = "spring.config.location="
        + "file:${user.home}/env/application.properties";

    public static void main(String[] args) {
        new SpringApplicationBuilder(HanghaeBoardProject2Application.class)
            .properties(APPLICATION_LOCATIONS)
            .run(args);
//        SpringApplication.run(HanghaeBoardProject2Application.class, args);
    }

}
