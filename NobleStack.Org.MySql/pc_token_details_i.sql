USE Noble_Stack;
DROP PROCEDURE IF EXISTS pc_token_details_i;
DELIMITER //

CREATE PROCEDURE pc_token_details_i(
	tok_type VARCHAR(4),
    tok_id VARCHAR(20),
    u_id INTEGER,
    app_id INTEGER,
    out tok_num INT
)
BEGIN
	IF EXISTS(SELECT token_number 
    FROM token_details WHERE token_type= tok_type AND token_id = tok_id 
							AND user_id = u_id AND application_id = app_id) THEN
                            
		SELECT token_number INTO tok_num FROM token_details 
				WHERE token_type= tok_type AND token_id = tok_id 
                AND user_id = u_id AND application_id = app_id;
                
    ELSE
		INSERT INTO token_details( 
						token_type,
						token_id,
                        user_id,
                        application_id
						) 
		VALUES	(
						tok_type,
						tok_id,
                        u_id,
                        app_id
						);
		SET tok_num = last_insert_id() ;
    END IF;
END//
