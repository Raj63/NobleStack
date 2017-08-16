USE Noble_Stack;
DROP PROCEDURE IF EXISTS pc_application_details_i;
DELIMITER //

CREATE PROCEDURE pc_application_details_i(
	app_name VARCHAR(20),
	description VARCHAR(1000),
    active_ind BOOL,
    out application_id INT
)
BEGIN
	INSERT INTO application_details( 
					application_name,
                    description, 
                    active_indicator
                    ) 
    VALUES	(
					app_name, 
					description, 
					active_ind
                    );
	SET application_id = last_insert_id();
END//
