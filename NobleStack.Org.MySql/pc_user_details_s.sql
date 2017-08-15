USE Noble_Stack;
DROP PROCEDURE IF EXISTS pc_user_details_i;
DELIMITER //

CREATE PROCEDURE pc_user_details_i(
	user_id INTEGER,
	fname VARCHAR(40),
    lname VARCHAR(40),
    age INTEGER,
    app_id INTEGER,
    act_ind BOOL
)
BEGIN
	INSERT INTO user_details( 
					user_id,
                    first_name,
                    last_name,
                    age,
                    application_id,
                    active_indicator
                    ) 
    VALUES	(
					user_id,
                    fname,
                    lname,
                    age,
                    app_id,
                    act_ind
                    );
END//
