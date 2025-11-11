package com.example.uberauth.services;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServices implements CommandLineRunner {

    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.secret}")
    private  String secret;

    /**
     * it will create brand new  jwt token based on payload>>>
     * here we have taken map because JSon objct can be analogy w.r.t as map data structures>>>
     */
    private String createToken(Map<String,Object> payload,String username){

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry);
        // JJWT now requires a Key object for signing
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

         return Jwts.builder()
                 .claims(payload)
                 .issuedAt(new Date(System.currentTimeMillis()))
                 .expiration(expiryDate)
                 .subject(username)
                 .signWith(key)
                 .compact();
                 
    }

    @Override
    public void run(String... args) throws Exception {

         Map<String,Object> payload = new HashMap<>();
         payload.put("email","Sanskar2@singh");
         payload.put("password","Sanskar@123");
        String token=createToken(payload,"Sanskar singh");
        System.out.println(token);

    }
}
