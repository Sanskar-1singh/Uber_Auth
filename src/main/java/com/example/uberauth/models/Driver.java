package com.example.uberauth.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@JsonIgnoreProperties({"hibernateLazyInitializer","handler","bookings"})//instead we have to use response Dtos>>>>
public class  Driver extends BaseModel{

    //DRIVER HAS MANY BOOKING 1:M

    private String name;

    @Column(nullable = false,unique = true)
    private String licencenumber;

    private String phoneNumber;

    private String aadharNumber;

    private String address;

    @OneToMany(mappedBy = "driver",fetch = FetchType.LAZY)
    private List<Bookings> bookings;

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", licencenumber='" + licencenumber + '\'' +
                ", bookings=" +
                '}';
    }
}
