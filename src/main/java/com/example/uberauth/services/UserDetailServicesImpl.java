package com.example.uberauth.services;

import com.example.uberauth.Repository.PassengerRepository;
import com.example.uberauth.helper.AuthPassengerDetails;
import com.example.uberentityservices.models.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * this class is responsible for loading the user in the form of userDetails object for the auth>>>
 * beacuse spring security can only take object of type userDetails for authntication mechanism>>
 */

@Service
public class UserDetailServicesImpl implements UserDetailsService {

    PassengerRepository  passengerRepository;

    public UserDetailServicesImpl(@Qualifier("passengerRepository") PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      Optional<Passenger> passenger =passengerRepository.findPassengerByEmail(email);//because email is unique identifier>>

      if(passenger.isPresent()){
         return new AuthPassengerDetails(passenger.get());
      }
      else{
          throw new UsernameNotFoundException("cannot find user by email");
      }
    }
}

/**
 * whenever spring want to fetch user of particular username it will came to this function and call this function and
 * this function will eventually return userDetails object>>>
 */