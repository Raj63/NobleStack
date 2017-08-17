/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementation;

import NobleStack.Org.DataAccess.RegisterUserDALC;
import NobleStack.Org.DataContracts.Accounts.RegisterRequest;
import NobleStack.Org.Utils.Common.Validator;
import java.sql.SQLException;

/**
 *
 * @author Ashu
 */
public class RegisterBLC {

    public int RegisterUser(RegisterRequest request) throws SQLException {
        if (request == null || request.User == null || request.User.UserDetails == null
                || request.User.ContactDetails == null || request.Application == null) {
            throw new IllegalArgumentException("Invalid Request");
        }
        RegisterUserDALC dalc = new RegisterUserDALC();
        request.Application.ApplicationId = dalc.SaveApplicationDetails(request.Application);
        request.User.UserDetails.UserId = dalc.SaveContactDetails(request.User.ContactDetails);
        String tokenNumber = null;
        if((request.MessagingToken!=null)){
            tokenNumber = dalc.SaveTokenDetails(request.MessagingToken.TokenId, request.MessagingToken.TokenType,
                    request.Application.ApplicationId, request.User.UserDetails.UserId);
        }
        dalc.SaveUserDetails(request.User.UserDetails, request.Application.ApplicationId, 
                request.User.UserDetails.UserId, tokenNumber);
        return request.User.UserDetails.UserId;
    }
}
