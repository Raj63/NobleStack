USE Noble_Stack;
DROP PROCEDURE IF EXISTS pc_message_queue_i;
DELIMITER //

CREATE PROCEDURE pc_message_queue_i(
	msg_txt BLOB,
	sender INT,
    receiver INT,
    app_id INT
)
BEGIN
	INSERT INTO message_queue(mq_description,mq_status,mq_sender,mq_receiver,mq_application_id)
    VALUES(msg_txt,1,sender,receiver,app_id);
END//
