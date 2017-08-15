/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementation;

import NobleStack.Org.DataAccess.RegisterUserDALC;
import NobleStack.Org.DataContracts.Accounts.RegisterRequest;

/**
 *
 * @author Ashu
 */
public class RegisterBLC {
    public void RegisterUser(RegisterRequest request){
        if(request==null || request.User == null || request.User.UserDetails == null 
                || request.Application == null){
            throw new IllegalArgumentException("Invalid Request");
        }
        RegisterUserDALC dalc = new RegisterUserDALC();
        dalc.SaveUserDetails(request.User.UserDetails, 
                dalc.SaveApplicationDetails(request.Application),
                dalc.SaveContactDetails(request.User.ContactDetails));
    }
}
