package com.example.uberauth.Dtos;


import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerSignUpDto {

     private String password;

     private String phonenumber;
     private String email;

     private String name;

}
