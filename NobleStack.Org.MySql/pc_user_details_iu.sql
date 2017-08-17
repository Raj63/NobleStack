USE Noble_Stack;
DROP PROCEDURE IF EXISTS pc_user_details_iu;
DELIMITER //

CREATE PROCEDURE pc_user_details_iu(
	u_id INTEGER,
	fname VARCHAR(40),
    lname VARCHAR(40),
    user_age INTEGER,
    app_id INTEGER,
    tok_num VARCHAR(255),
    act_ind BOOL
)
BEGIN
	IF NOT EXISTS(SELECT user_id FROM user_details WHERE user_id = u_id AND application_id = app_id) THEN
		INSERT INTO user_details( 
						user_id,
						first_name,
						last_name,
						age,
						application_id,
                        token_number,
						active_indicator
						) 
		VALUES	(
						u_id,
						fname,
						lname,
						user_age,
						app_id,
                        tok_num,
						act_ind
						);
	ELSE 
		UPDATE user_details 
        SET token_number = tok_num
        WHERE user_id = u_id AND application_id = app_id;
	END IF;
END//
