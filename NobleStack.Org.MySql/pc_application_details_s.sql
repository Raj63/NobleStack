USE Noble_Stack;
DROP PROCEDURE IF EXISTS pc_application_details_s;
DELIMITER //

CREATE PROCEDURE pc_application_details_s(
	app_name VARCHAR(60),
    out appid INT
)
BEGIN
	SELECT application_id INTO appid FROM application_details WHERE application_name = app_name;
END//
