/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NobleStack.Org.DataContracts.Accounts;

import NobleStack.Org.DataContracts.Common.MessagingToken;
import NobleStack.Org.DataContracts.Common.Application;

/**
 *
 * @author Ashu
 */
public class RegisterRequest {
    public User User;
    public Application Application;
    public MessagingToken MessagingToken;
}
