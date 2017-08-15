/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NobleStack.Org.DataAccess;

import NobleStack.Org.DataContracts.Accounts.ContactDetails;
import NobleStack.Org.DataContracts.Accounts.RegisterRequest;
import NobleStack.Org.DataContracts.Accounts.UserDetails;
import NobleStack.Org.DataContracts.Common.Application;
import NobleStack.Org.Utils.Common.Validator;
import NobleStack.Org.Utils.DataAccess.ConnectionHelper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ashu
 */
public class RegisterUserDALC {

    public int SaveApplicationDetails(Application request) {
        int applicationId = 0;
        try {
            //save in database
            if (request == null || Validator.IsNullOrWhiteSpace(request.ApplicationName)
                    || Validator.IsNullOrWhiteSpace(request.Description)) {
                throw new IllegalArgumentException("Invalid Request");
            }
            Connection conn = ConnectionHelper.getConnection();
            CallableStatement stmt = conn.prepareCall("{call pc_application_details_i(?,?,?}");
            stmt.setString("app_name", request.ApplicationName);
            stmt.setString("description", request.Description);
            stmt.setBoolean("active_ind", true);
            try (ResultSet res = stmt.executeQuery()) {
                res.next();
                applicationId = res.getInt("application_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterUserDALC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return applicationId;
    }
    
    public int SaveContactDetails(ContactDetails request) {
        int userId = 0;
        try {
            //save in database
            if (request == null 
                    || Validator.IsNullOrWhiteSpace(request.EmailAddress)
                    || Validator.IsNullOrWhiteSpace(request.PhoneNumber)) {
                throw new IllegalArgumentException("Invalid Request");
            }
            Connection conn = ConnectionHelper.getConnection();
            CallableStatement stmt = conn.prepareCall("{call pc_contact_details_i(?,?}");
            stmt.setString("email_id", request.EmailAddress);
            stmt.setString("phone_number", request.PhoneNumber);
            try (ResultSet res = stmt.executeQuery()) {
                res.next();
                userId = res.getInt("user_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterUserDALC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userId;
    }
    public void SaveUserDetails(UserDetails userDetails, int applicationId, int userId) {
        
        try {
            //save in database
            if (userDetails == null 
                    || applicationId == 0 || userId == 0
                    || Validator.IsNullOrWhiteSpace(userDetails.FirstName)
                    || Validator.IsNullOrWhiteSpace(userDetails.LastName)
                    || userDetails.age == 0) {
                throw new IllegalArgumentException("Invalid Request");
            }
            Connection conn = ConnectionHelper.getConnection();
            CallableStatement stmt = conn.prepareCall("{call pc_user_details_i(?,?,?,?,?,?}");
            stmt.setInt("user_id", userId);
            stmt.setString("fname", userDetails.FirstName);
            stmt.setString("lname", userDetails.LastName);
            stmt.setInt("fname", userDetails.age);
            stmt.setInt("app_id", applicationId);
            stmt.setBoolean("act_ind",true);
            stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(RegisterUserDALC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
