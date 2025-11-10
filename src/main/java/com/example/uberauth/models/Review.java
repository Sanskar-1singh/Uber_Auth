package com.example.uberauth.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booking_review")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter//only work at database level>>>

@Inheritance(strategy = InheritanceType.JOINED)
public class Review extends BaseModel{

    @Column(nullable = false)
   private String content;

    @Column(nullable = false)
   private Double rating;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Bookings booking;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", createdAt=" + createdAt +
                   booking.getBookingStatus()+
                ", updatedAt=" + updatedAt +
                '}';
    }
}
