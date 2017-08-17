USE Noble_Stack;
DROP PROCEDURE IF EXISTS pc_application_details_i;
DELIMITER //

CREATE PROCEDURE pc_application_details_i(
	app_name VARCHAR(20),
	descr VARCHAR(1000),
    active_ind BOOL,
    out app_id INT
)
BEGIN
	IF EXISTS(SELECT application_id FROM application_details 
					   WHERE application_name = app_name AND description = descr AND active_indicator = active_ind) THEN
		
        select application_id into app_id	FROM application_details 
					   WHERE application_name = app_name AND description = descr AND active_indicator = active_ind;
	
    ELSE
    
	INSERT INTO application_details( 
					application_name,
                    description, 
                    active_indicator
                    ) 
    VALUES	(
					app_name, 
					descr, 
					active_ind
                    );
	SET app_id = last_insert_id();
    END IF;
END//
