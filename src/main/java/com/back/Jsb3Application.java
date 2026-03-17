package com.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Jsb3Application {

    public static void main(String[] args) {
        SpringApplication.run(Jsb3Application.class, args);
    }

}
