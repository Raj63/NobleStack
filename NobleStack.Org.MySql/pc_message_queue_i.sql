USE Noble_Stack;
DROP PROCEDURE IF EXISTS pc_message_queue_i;
DELIMITER //

CREATE PROCEDURE pc_message_queue_i(
	msg_txt BLOB,
	sender INT,
    receiver INT
)
BEGIN
	INSERT INTO message_queue(mq_description,mq_status,mq_sender,mq_receiver)
    VALUES(msg_txt,1,sender,receiver);
END//
