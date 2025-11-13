package com.example.uberauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan("com.example.uberentityservices.models")
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
 */

/**
 * 🧩 What is @Bean (in simple words)
 * @Bean just means
 * ➡️ “Hey Spring, please make this object for me and manage it so I can use it anywhere in my project.”
 * 🧠 Think of it like this:
 * When your app starts, Spring creates and keeps all important objects (called beans) inside a big box called the Application Context.
 *
 * If you mark something with @Bean, you’re basically telling Spring:
 * “Add this object into your box so I can use it later.”
 * 🧰 Example
 * @Configuration
 * public class AppConfig {
 *
 *     @Bean
 *     public PasswordEncoder passwordEncoder() {
 *         return new BCryptPasswordEncoder();
 *     }
 * }
 * 🧩 What happens:
 * When your app starts, Spring calls passwordEncoder().
 * It gets a BCryptPasswordEncoder object.
 * Spring stores it in its box (the context).
 * Now, anywhere in your project you can do:
 *
 * @Autowired
 * private PasswordEncoder passwordEncoder;

 * Spring will automatically give you that same object — you don’t have to create it yourself.
 */