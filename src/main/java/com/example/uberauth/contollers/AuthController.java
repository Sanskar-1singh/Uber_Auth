package com.example.uberauth.contollers;


import com.example.uberauth.Dtos.PassengerDto;
import com.example.uberauth.Dtos.PassengerSignUpDto;
import com.example.uberauth.services.AuthServices;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthServices authServices;

    public AuthController(@Qualifier("authServices") AuthServices authServices) {
        this.authServices = authServices;
    }

    @PostMapping("/signUp")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignUpDto request){

        System.out.println("signUp");
       PassengerDto passengerDto= authServices.signupRequest(request);
       return new ResponseEntity<>(passengerDto, HttpStatus.CREATED);

    }

    @GetMapping("/signin")
    public ResponseEntity<?> signin(){

        return new ResponseEntity<>(10,HttpStatus.OK);
    }

}
