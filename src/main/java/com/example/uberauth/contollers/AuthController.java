package com.example.uberauth.contollers;


import com.example.uberauth.Dtos.PassengerDto;
import com.example.uberauth.Dtos.PassengerSignUpDto;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/signUp")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignUpDto request){

        return ResponseEntity.ok(new PassengerDto());

    }
}
