CREATE SCHEMA Noble_Stack;

USE Noble_Stack;

CREATE TABLE application_details(
	application_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    application_name VARCHAR(20) NOT NULL,
    description VARCHAR(1000),
    active_indicator BOOL NOT NULL DEFAULT TRUE
);

CREATE TABLE contact_details(
	user_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email_id VARCHAR(60) NOT NULL,
    phone_number VARCHAR(13) NOT NULL
);

CREATE TABLE user_details(
	user_id INTEGER NOT NULL REFERENCES contact_details(user_id),
	first_name VARCHAR(40) NOT NULL ,
    last_name VARCHAR(40) NOT NULL ,
    age INTEGER,
    application_id INTEGER REFERENCES application_details(application_id),
    active_indicator BOOL NOT NULL DEFAULT TRUE
);
