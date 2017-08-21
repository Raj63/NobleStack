/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NobleStack.Org.Utils.Common;

import NobleStack.Org.DataContracts.Accounts.ContactDetails;

/**
 *
 * @author Ashu
 */
public final class Validator {
    public static boolean IsNullOrWhiteSpace(String input){
        return input == null || (input.length() > 0 && input.trim().length()<=0);
    }
    
    public static boolean ValidateContact(ContactDetails input){
        return input!=null && (input.EmailAddress !=null || input.PhoneNumber!=null);
    }
}
