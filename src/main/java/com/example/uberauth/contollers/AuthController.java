package com.example.uberauth.contollers;


import com.example.uberauth.Dtos.AuthRequestDto;
import com.example.uberauth.Dtos.PassengerDto;
import com.example.uberauth.Dtos.PassengerSignUpDto;
import com.example.uberauth.services.AuthServices;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthServices authServices;

    private AuthenticationManager  authenticationManager;

    public AuthController(@Qualifier("authServices") AuthServices authServices,
                          AuthenticationManager authenticationManager) {
        this.authServices = authServices;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignUpDto request){

        System.out.println("signUp");
       PassengerDto passengerDto= authServices.signupRequest(request);
       return new ResponseEntity<>(passengerDto, HttpStatus.CREATED);

    }

    @PostMapping("/signin/passenger")
    public ResponseEntity<?> signin(@RequestBody AuthRequestDto  authRequestDto){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(),authRequestDto.getPassword()));
          if(authentication.isAuthenticated()){
              return new ResponseEntity<>("Sucess",HttpStatus.OK);
          }
          else{
              return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
          }
    }

}


/**
 *
 * OAuth2.0
 * login via email password
 * login via google /fb/lionkedin
 * login through SSO
 * login via mobile SMS or OTP
 *
 * here all the above we do not have to share credeential with everyone>>>
 *
 * FOUR MAIN COMPONENT OF AUTH COMPONENT->>>>
 * RESOURCE  OWNER->USER ITSELF
 * CLIENT->BOOK MY SHOW
 * RESOURCE SERVER->SERVER OF LEETCODE,BMS
 * AUTH SERVER->GOOGLE FB GITHUB SERVER
 *
 *
 */