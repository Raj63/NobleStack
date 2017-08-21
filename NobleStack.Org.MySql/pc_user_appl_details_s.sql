USE Noble_Stack;
DROP PROCEDURE IF EXISTS pc_user_appl_details_s;
DELIMITER //

CREATE PROCEDURE pc_user_appl_details_s(
	email_addr VARCHAR(60),
    phn_num VARCHAR(13),
    app_name VARCHAR(20),
    u_id INT
)
BEGIN
	SELECT ud.user_id INTO u_id
    FROM user_details ud 
    INNER JOIN application_details ad ON ud.application_id = ad.application_id
    INNER JOIN contact_details cd ON ud.user_id = cd.user_id
    WHERE ad.application_name = app_name AND cd.phone_number = phn_num AND cd.email_id = email_addr;
END//
