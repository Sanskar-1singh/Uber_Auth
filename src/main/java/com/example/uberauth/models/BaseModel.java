package com.example.uberauth.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@EntityListeners(AuditingEntityListener.class)

@MappedSuperclass

@Getter
@Setter
public abstract class BaseModel {
    @Id//this annotation makes the id property as primary key of my table
    @GeneratedValue(strategy = GenerationType.IDENTITY)//it automatically generated value rather than explicitly providing it>>>
    protected Long id;


    @Column(nullable = false)//to give properties to column of this table>>>>
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date updatedAt;
}

/**
 * @MappedSuperclass annotation is used for => it is given to parent class
 * it is used to promote Single responsibilty principle>>>>
 * No table for the parent class
 * One table for each child class having its own attributes and parent class attributes>>> inheritence in JPA
 *
 *
 * now imagine there is case in which we want separate class for parent also and sperate class for child also
 * so there exists total 3 strategy through which we can solve problem
 * 1->SINGLE TABLE
 * 2->TABLE PER CLASS
 * 3->JOINED TABLE
 *
 * @SingleTable->one big table include all the property of parent class and its child class also.This is single table inheritence
 *
 * @TABLE_PER_CLASS->everything same as mappedsuperclass but also each table per class and with all inherited attributes and along with base class table
 *
 * @JOINED_TABLE->to create a joins among the entity and this is known as joied inheritence it involve normalisation>>>
 *
 * @PrimaryKeyJoinColumn(name = "driver_review_id") to give custom name to foreign key coloumn in case of joined_table
 *  inheritence>>>
 */
