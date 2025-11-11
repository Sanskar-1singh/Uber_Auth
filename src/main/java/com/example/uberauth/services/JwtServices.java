package com.example.uberauth.services;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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
    private String createToken(Map<String,Object> payload,String email){

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry);
        // JJWT now requires a Key object for signing
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

         return Jwts.builder()
                 .setClaims(payload)
                 .setSubject(email)
                 .setIssuedAt(now)
                 .setExpiration(expiryDate)
                 .signWith(key, SignatureAlgorithm.HS256)
                 .compact();
                 
    }

    public Claims extractPayload(String token){
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
           return  Jwts.parserBuilder()
                   .setSigningKey(key)
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

   public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims=extractPayload(token);
        return claimsResolver.apply(claims);
   }


    private Date extractExpiration(String token) {
       return extractClaims(token, Claims::getExpiration);

    }

    private Boolean isTokenExpired(String token){
        //this method check if token expiry is before current timestamp>>>
        //true if token is expire or false it not expired>>>
        return extractExpiration(token).before(new Date());
      }

      private String extractEmail(String token) {
        return extractClaims(token, Claims::getSubject);
      }


      private Boolean validateToken(String token,String email) {
        final String userEmail=extractEmail(token);
         return (userEmail.equals(email)) &&  (!isTokenExpired(token));
      }

      public String getPhoneNumber(String token){
        Claims claim=extractPayload(token);
        String number=(String) claim.get("phoneNumber");
        return number;
      }

      private String extractKeyPayload(String token,String key){
        Claims claims=extractPayload(token);
        return (String)claims.get(key);
      }


    @Override//it is just for extra information retentiuon policy is compile time
    public void run(String... args) throws Exception {

         Map<String,Object> payload = new HashMap<>();
         payload.put("email","Sanskar2@singh");
         payload.put("password","Sanskar@123");
        String token=createToken(payload,"Sanskar singh");
        System.out.println(token);
        String x=extractKeyPayload(token,"phoneNumber");
        System.out.println(x);

    }
}
