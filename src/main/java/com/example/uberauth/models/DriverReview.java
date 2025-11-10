package com.example.uberauth.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DriverReview extends Review {

    private String DriverReviewContent;
}


/**
 * COMPOSITION IN SPRING BOOT JPA DATA->>>>
 *
 *1:1 relation
 * 1:n relation
 *
 * M:1 relation
 * M:M relation
 *
 * we represent composition using has_a relation of LLD concept yes you know that corectly>>>
 *
 *
 *
 */