package com.example.uberauth.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Bookings extends BaseModel{

    @Enumerated(value=EnumType.STRING)
    private BookingStatus bookingStatus; //HAS_A RELATION(COMPOSITION)

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    private Long totalDistance;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Driver driver;//why not booking join eagerly making join on Driver table????

    @ManyToOne(cascade = CascadeType.ALL)
    private Passenger passenger;


}


/**
 * relation between Booking and review
 * 1 booking has one review and one review has one booking i.e. 1:1 relation>>>>
 *
 * 1: 1 anyone can have foreign key>>>
 *
 * now 1:M assocaitions
 *  a driver has many review but review belongs to a one driver
 *  1 : M->foreign key contains
 *
 *  Now M:M associations
 *here we required a join table
 *
 * Doctor has many patient through join table
 * Patient has many doctor thorugh join table
 *
 * @OneToOne->Eager
 * OneToMAny->LAZY
 * ManyToMany->LAzy
 * ManyToOne->Eager
 *
 *
 *
 *
 *
 */
