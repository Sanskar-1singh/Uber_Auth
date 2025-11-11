package com.example.uberauth.Dtos;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {

    private String email;
    private String password;

}
