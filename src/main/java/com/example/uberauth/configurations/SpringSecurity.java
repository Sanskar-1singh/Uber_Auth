package com.example.uberauth.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {

 // IT IS VERY IMPORTANT BEACUSE when we include spring security our all api beacome auth protected therefore by adding belo w
    //configuration we allow request to bypass the authentications>>>
     @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
         return http.csrf(csrf->csrf.disable())
                 .authorizeHttpRequests(auth->auth.requestMatchers("/api/v1/auth/signUp/**").permitAll()
                         .requestMatchers("api/v1/auth/signin/**").permitAll())
                 .build();
     }
    /**
     * it is very important to give
     * configuratuion like this because there are multiple constructor of BcryptPasswordEncode
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
