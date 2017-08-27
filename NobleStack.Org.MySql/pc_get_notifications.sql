USE Noble_Stack;
DROP PROCEDURE IF EXISTS pc_get_notifications;
DELIMITER //

CREATE PROCEDURE pc_get_notifications(
	userid INT,
    applicationid INT
)
BEGIN
    SELECT mq.mq_id, mq.mq_description, cd.email_id, cd.phone_number, ad.application_id
    FROM message_queue mq 
    INNER JOIN user_details ud ON mq.mq_receiver = ud.user_id 
    INNER JOIN application_details ad ON ad.application_id = ud.application_id
    INNER JOIN contact_details cd ON cd.user_id = ud.user_id
    WHERE mq_receiver = userid AND ad.application_id = applicationid;
END//
