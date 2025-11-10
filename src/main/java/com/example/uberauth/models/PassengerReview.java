package com.example.uberauth.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "driver_review_id")
public class PassengerReview extends Review {
     
    @Column(nullable =false)
    private String passengerReviewContent;
    private String passengerRating;

}
