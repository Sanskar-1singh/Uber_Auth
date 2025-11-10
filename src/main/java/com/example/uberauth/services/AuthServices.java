package com.example.uberauth.services;


import com.example.uberauth.Dtos.PassengerDto;
import com.example.uberauth.Dtos.PassengerSignUpDto;
import com.example.uberauth.Repository.PassengerRepository;
import com.example.uberauth.models.Passenger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    private PassengerRepository passengerRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthServices(@Qualifier("passengerRepository") PassengerRepository passengerRepository
            ,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.passengerRepository = passengerRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public PassengerDto signupRequest(PassengerSignUpDto passengerSignUpDto){
        Passenger passenger=Passenger.builder()
                .email(passengerSignUpDto.getEmail())
                .name(passengerSignUpDto.getName())
                .password(bCryptPasswordEncoder.encode(passengerSignUpDto.getPassword()))
                .phoneNumber(passengerSignUpDto.getPhonenumber())
                .build();

           Passenger newPassenger=passengerRepository.save(passenger);
           System.out.println(newPassenger);

           PassengerDto passengerDto=PassengerDto.from(newPassenger);

        return passengerDto;

    }

}
