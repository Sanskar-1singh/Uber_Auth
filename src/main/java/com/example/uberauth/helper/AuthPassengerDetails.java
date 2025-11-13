package com.example.uberauth.helper;


import com.example.uberentityservices.models.Passenger;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AuthPassengerDetails extends Passenger implements UserDetails {

      private String username;
      private String password;

      public AuthPassengerDetails(Passenger passenger) {
          this.username = passenger.getEmail();
          this.password = passenger.getPassword();
      }


         @Override
        public String getPassword() {
             return this.password;
         }
         @Override
        public String getUsername() {
            return this.username;
         }
    // below set of method di not of much concern
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }
}

/**
 * spring security always do authenticate on userDetail object therefore it is very important to convert our object into polymorphic
 * of userDetails type of object>>
 *
 *
 */
