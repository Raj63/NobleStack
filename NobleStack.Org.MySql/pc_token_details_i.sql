USE Noble_Stack;
DROP PROCEDURE IF EXISTS pc_token_details_i;
DELIMITER //

CREATE PROCEDURE pc_token_details_i(
	tok_type VARCHAR(4),
    tok_id VARCHAR(20),
    out tok_num INT
)
BEGIN
	IF EXISTS(SELECT token_number 
    FROM token_details WHERE token_type= tok_type AND token_id = tok_id) THEN
                            
		SELECT token_number INTO tok_num FROM token_details 
				WHERE token_type= tok_type AND token_id = tok_id;
                
    ELSE
		INSERT INTO token_details( 
						token_type,
						token_id
						) 
		VALUES	(
						tok_type,
						tok_id
						);
		SET tok_num = last_insert_id() ;
    END IF;
END//
