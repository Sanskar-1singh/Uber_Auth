package com.example.uberauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UberAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(UberAuthApplication.class, args);
    }

}

/**
 *
 * there exists two kind of algo->
 * Encrytption algorithm
 *and there is hashing algorithm
 *
 * hashed password can be get revert
 * and encrypted password can be decrypt passowrd>>.
 *
 *
 */