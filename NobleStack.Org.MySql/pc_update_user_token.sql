USE Noble_Stack;
DROP PROCEDURE IF EXISTS pc_update_user_token;
DELIMITER //

CREATE PROCEDURE pc_update_user_token(
	uid INTEGER,
    tok_number INTEGER
)
BEGIN
	UPDATE user_details
    SET token_number = tok_number
    WHERE user_id = u_id;
END//
