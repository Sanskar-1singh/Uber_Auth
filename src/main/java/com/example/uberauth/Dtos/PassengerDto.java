package com.example.uberauth.Dtos;

import com.example.uberentityservices.models.Passenger;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

    private String id;
    private String name;
    private String email;
    private String password;

    private String phoneNumber;
    private Date creationDate;


    public static PassengerDto from(Passenger passenger) {
        PassengerDto passengerDto = PassengerDto.builder()
                .id(passenger.getId().toString())
                .creationDate(passenger.getCreatedAt())
                .email(passenger.getEmail())
                .password(passenger.getPassword())
                .name(passenger.getName())
                .phoneNumber(passenger.getPhoneNumber())
                .build();


            return passengerDto;
    }


}


