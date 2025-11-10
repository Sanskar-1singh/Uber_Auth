CREATE TABLE booking_review
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NOT NULL,
    content    VARCHAR(255)          NOT NULL,
    rating     DOUBLE                NOT NULL,
    CONSTRAINT pk_booking_review PRIMARY KEY (id)
);

CREATE TABLE bookings
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NOT NULL,
    review_id      BIGINT                NULL,
    booking_status ENUM('SCHEDULE','CANCELLED','COMPLETED') NULL,
    start_time     datetime              NULL,
    end_time       datetime              NULL,
    total_distance BIGINT                NULL,
    driver_id      BIGINT                NULL,
    passenger_id   BIGINT                NULL,
    CONSTRAINT pk_bookings PRIMARY KEY (id)
);

CREATE TABLE driver
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime              NOT NULL,
    updated_at    datetime              NOT NULL,
    name          VARCHAR(255)          NULL,
    licencenumber VARCHAR(255)          NOT NULL,
    phone_number  VARCHAR(255)          NULL,
    aadhar_number VARCHAR(255)          NULL,
    address       VARCHAR(255)          NULL,
    CONSTRAINT pk_driver PRIMARY KEY (id)
);

CREATE TABLE driver_review
(
    id                    BIGINT       NOT NULL,
    driver_review_content VARCHAR(255) NULL,
    CONSTRAINT pk_driverreview PRIMARY KEY (id)
);

CREATE TABLE passenger
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NOT NULL,
    name       VARCHAR(255)          NULL,
    CONSTRAINT pk_passenger PRIMARY KEY (id)
);

CREATE TABLE passenger_review
(
    driver_review_id         BIGINT       NOT NULL,
    passenger_review_content VARCHAR(255) NOT NULL,
    passenger_rating         VARCHAR(255) NULL,
    CONSTRAINT pk_passengerreview PRIMARY KEY (driver_review_id)
);

ALTER TABLE driver
    ADD CONSTRAINT uc_driver_licencenumber UNIQUE (licencenumber);

ALTER TABLE bookings
    ADD CONSTRAINT FK_BOOKINGS_ON_DRIVER FOREIGN KEY (driver_id) REFERENCES driver (id);

ALTER TABLE bookings
    ADD CONSTRAINT FK_BOOKINGS_ON_PASSENGER FOREIGN KEY (passenger_id) REFERENCES passenger (id);

ALTER TABLE bookings
    ADD CONSTRAINT FK_BOOKINGS_ON_REVIEW FOREIGN KEY (review_id) REFERENCES booking_review (id);

ALTER TABLE driver_review
    ADD CONSTRAINT FK_DRIVERREVIEW_ON_ID FOREIGN KEY (id) REFERENCES booking_review (id);

ALTER TABLE passenger_review
    ADD CONSTRAINT FK_PASSENGERREVIEW_ON_DRIVER_REVIEW FOREIGN KEY (driver_review_id) REFERENCES booking_review (id);