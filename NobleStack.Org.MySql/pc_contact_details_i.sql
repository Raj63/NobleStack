USE Noble_Stack;
DROP PROCEDURE IF EXISTS pc_contact_details_i;
DELIMITER //

CREATE PROCEDURE pc_contact_details_i(
	email_id VARCHAR(60),
    phone_number VARCHAR(13)
)
BEGIN
	INSERT INTO application_details( 
					email_id,
                    phone_number
                    ) 
    VALUES	(
					email_id,
                    phone_number
                    );
	SELECT last_insert_id() AS user_id;
END//
