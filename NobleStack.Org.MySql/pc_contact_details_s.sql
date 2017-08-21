USE Noble_Stack;
DROP PROCEDURE IF EXISTS pc_contact_details_s;
DELIMITER //

CREATE PROCEDURE pc_contact_details_s(
	email_adr VARCHAR(60),
    p_num VARCHAR(13),
    out uid INT
)
BEGIN
	IF EXISTS(SELECT user_id FROM contact_details WHERE email_id= email_adr AND phone_number = p_num) THEN
		SELECT user_id INTO uid FROM contact_details WHERE email_id= email_adr AND phone_number = p_num;
    END IF;
END//
