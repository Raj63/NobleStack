
CREATE SCHEMA Noble_Stack;

USE Noble_Stack;

CREATE TABLE ref_message_status(
	rms_id INT,
    rms_value VARCHAR(10)
);

INSERT INTO ref_message_status(rms_id, rms_value) 
VALUES (1, 'Sent'),(2, 'Delivered'),(3, 'Read');

CREATE TABLE message_queue(
	mq_id INT AUTO_INCREMENT PRIMARY KEY,
    mq_status INT REFERENCES ref_message_status(rms_id),
    mq_description BLOB,
    mq_sender INT REFERENCES user_details(user_id),
    mq_receiver INT REFERENCES user_details(user_id),
    mq_application_id INT REFERENCES application_details(application_id)
);