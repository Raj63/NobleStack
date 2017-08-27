/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NobleStack.Org.DataAccess;

import NobleStack.Org.DataContracts.Accounts.ContactDetails;
import NobleStack.Org.DataContracts.Accounts.UserDetails;
import NobleStack.Org.DataContracts.Common.Application;
import NobleStack.Org.DataContracts.Common.TokenType;
import NobleStack.Org.Utils.Common.Validator;
import NobleStack.Org.Utils.DataAccess.ConnectionHelper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author Ashu
 */
public class RegisterUserDALC {

    public String SaveApplicationDetails(Application request) throws SQLException {

        //save in database
        if (request == null || Validator.IsNullOrWhiteSpace(request.ApplicationName)
                || Validator.IsNullOrWhiteSpace(request.Description)) {
            throw new IllegalArgumentException("Invalid Request");
        }
        Connection conn = ConnectionHelper.getConnection();
        String applicationId = "";
        try {
            CallableStatement stmt = conn.prepareCall("{call pc_application_details_i(?,?,?,?)}");
            stmt.setString("app_name", request.ApplicationName);
            stmt.setString("descr", request.Description);
            stmt.setBoolean("active_ind", true);
            stmt.registerOutParameter("app_id", Types.INTEGER);
            stmt.execute();

            stmt.getResultSet();

            applicationId = Integer.toString(stmt.getInt("app_id"));

        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return applicationId;
    }

    public int SaveContactDetails(ContactDetails request) throws SQLException {

        //save in database
        if (request == null
                || Validator.IsNullOrWhiteSpace(request.EmailAddress)
                || Validator.IsNullOrWhiteSpace(request.PhoneNumber)) {
            throw new IllegalArgumentException("Invalid Request");
        }
        int userId = 0;
        Connection conn = ConnectionHelper.getConnection();
        try {
            CallableStatement stmt = conn.prepareCall("{call pc_contact_details_i(?,?,?)}");
            stmt.setString("email_adr", request.EmailAddress);
            stmt.setString("p_num", request.PhoneNumber);
            stmt.registerOutParameter("uid", Types.INTEGER);
            stmt.execute();
            userId = stmt.getInt("uid");

        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return userId;
    }

    public void SaveUserDetails(UserDetails userDetails, String applicationId, int userId, String tokenNumber) throws SQLException {
        if (userDetails == null
                || applicationId.equals("0") || userId == 0
                || Validator.IsNullOrWhiteSpace(userDetails.FirstName)
                || Validator.IsNullOrWhiteSpace(userDetails.LastName)
                || userDetails.age == 0) {
            throw new IllegalArgumentException("Invalid Request");
        }
        Connection conn = ConnectionHelper.getConnection();
        try {
            //save in database

            CallableStatement stmt = conn.prepareCall("{call pc_user_details_iu(?,?,?,?,?,?,?)}");
            stmt.setInt("u_id", userId);
            stmt.setString("fname", userDetails.FirstName);
            stmt.setString("lname", userDetails.LastName);
            stmt.setInt("user_age", userDetails.age);
            stmt.setInt("app_id", Integer.parseInt(applicationId));
            stmt.setString("tok_num", Validator.IsNullOrWhiteSpace(tokenNumber) ? null : tokenNumber);
            stmt.setBoolean("act_ind", true);
            stmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String SaveTokenDetails(String tokenId, TokenType tokenType) throws SQLException {

        String tokenNumber = null;

        Connection conn = ConnectionHelper.getConnection();
        try {
            //save in database
            if (Validator.IsNullOrWhiteSpace(tokenId)) {
                throw new IllegalArgumentException("Invalid Request");
            }
            CallableStatement stmt = conn.prepareCall("{call pc_token_details_i(?,?,?)}");
            stmt.setString("tok_type", String.valueOf(tokenType));
            stmt.setString("tok_id", tokenId);
            stmt.registerOutParameter("tok_num", Types.INTEGER);
            stmt.execute();
            tokenNumber = Integer.toString(stmt.getInt("tok_num"));
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return tokenNumber;
    }

    public String GetUserId(ContactDetails request) throws SQLException {
        if (request == null || Validator.IsNullOrWhiteSpace(request.EmailAddress)
                || Validator.IsNullOrWhiteSpace(request.PhoneNumber)) {
            throw new IllegalArgumentException();
        }

        Connection conn = ConnectionHelper.getConnection();
        String userId = "";
        try {
            CallableStatement stmt = conn.prepareCall("{call pc_contact_details_s(?,?,?)}");
            stmt.setString("email_adr", request.EmailAddress);
            stmt.setString("p_num", request.PhoneNumber);
            stmt.registerOutParameter("uid", Types.INTEGER);
            stmt.execute();
            userId = Integer.toString(stmt.getInt("uid"));
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return userId;
    }

    public void UpdateUserMessagingToken(String userId, String tokenNumber) throws SQLException {
        if (Validator.IsNullOrWhiteSpace(userId)
                || Validator.IsNullOrWhiteSpace(tokenNumber)) {
            throw new IllegalArgumentException();
        }
        Connection conn = ConnectionHelper.getConnection();
        try {

            CallableStatement stmt = conn.prepareCall("{call pc_user_details_update_token(?,?)}");
            stmt.setInt("email_adr", Integer.parseInt(userId));
            stmt.setInt("tok_number", Integer.parseInt(tokenNumber));
            stmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String GetApplicationId(Application application) throws SQLException {
        if (application == null || Validator.IsNullOrWhiteSpace(application.ApplicationName)) {
            throw new IllegalArgumentException();
        }
        String applicationId = "";
        Connection conn = ConnectionHelper.getConnection();
        try {

            CallableStatement stmt = conn.prepareCall("{call pc_application_details_s(?,?)}");
            stmt.setString("app_name", application.ApplicationName);
            stmt.registerOutParameter("appid", Types.INTEGER);
            stmt.execute();
            applicationId = Integer.toString(stmt.getInt("appid"));
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return applicationId;
    }
}
