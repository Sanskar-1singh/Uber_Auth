package com.example.uberauth.contollers;


import com.example.uberauth.Dtos.AuthRequestDto;
import com.example.uberauth.Dtos.AuthResponseDto;
import com.example.uberauth.Dtos.PassengerDto;
import com.example.uberauth.Dtos.PassengerSignUpDto;
import com.example.uberauth.services.AuthServices;
import com.example.uberauth.services.JwtServices;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthServices authServices;

    @Value("${cookie.expiry}")
    private int CookieExpiry;

    private JwtServices  jwtServices;

    private AuthenticationManager  authenticationManager;

    public AuthController(@Qualifier("authServices") AuthServices authServices,
                          AuthenticationManager authenticationManager,
                          @Qualifier("jwtServices") JwtServices jwtServices) {
        this.authServices = authServices;
        this.jwtServices = jwtServices;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignUpDto request){

        System.out.println("signUp");
       PassengerDto passengerDto= authServices.signupRequest(request);
       return new ResponseEntity<>(passengerDto, HttpStatus.CREATED);

    }

    @PostMapping("/signin/passenger")
    public ResponseEntity<?> signin(@RequestBody AuthRequestDto  authRequestDto, HttpServletResponse httpServletResponse){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(),authRequestDto.getPassword()));
          if(authentication.isAuthenticated()){//at this step authentication has happen already we are just creating JWT token>>
              String jwtToken=jwtServices.createToken(authRequestDto.getEmail());

              //here we have done cookie creations
              ResponseCookie cookie=ResponseCookie.from("jwtToken",jwtToken)
                      .httpOnly(true)
                      .secure(false)
                      .path("/")
                      .maxAge(CookieExpiry)
                      .build();

              //here we are setting cookie in our headers>>>
              httpServletResponse.setHeader(HttpHeaders.SET_COOKIE,cookie.toString());
              return new ResponseEntity<>(AuthResponseDto.builder().status(true).build(),HttpStatus.OK);
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
 * NOW WE HAVE TO SEND JWT TOKEN INTO HTTPONLY COOKIW SO THAT IT CANNOT BE ACCESSIBE FROM BROWSER>>>
 *
 *
 */