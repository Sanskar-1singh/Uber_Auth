package com.example.uberauth.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","bookings"})
public class Passenger extends BaseModel {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", bookings=" + bookings +
                '}';
    }

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


    @OneToMany(mappedBy = "passenger")
    private List<Bookings> bookings= new ArrayList<>();

}

/**
 * IN this project we have learnt about
 * 1-> DB associations
 * 2->Migrations
 * 3->lazy loading and eager loading
 * 4->n+1 problem
 * 5->inheritence in jpa
 * 6->jpa queries
 * 7->composition
 * 8-cascading in db
 * 9->entity creation in class format
 *
 *
 * 10->diffrent kind of annotations
 * 11->dependency injection
 * 12->about build system like gradle>>>
 *
 *
 */