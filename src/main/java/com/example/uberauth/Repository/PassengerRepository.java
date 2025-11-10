package com.example.uberauth.Repository;


import com.example.uberauth.Dtos.PassengerDto;
import com.example.uberauth.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long> {



}
