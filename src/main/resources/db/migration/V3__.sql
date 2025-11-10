
ALTER TABLE passenger
    ADD password VARCHAR(255) NULL;

ALTER TABLE passenger
    ADD phone_number VARCHAR(255) NULL;

ALTER TABLE passenger
    MODIFY email VARCHAR(255) NOT NULL;

ALTER TABLE passenger
    MODIFY password VARCHAR(255) NOT NULL;

ALTER TABLE passenger
    MODIFY phone_number VARCHAR(255) NOT NULL;

ALTER TABLE passenger
    MODIFY name VARCHAR(255) NOT NULL;