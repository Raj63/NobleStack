/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NobleStack.Org.DataContracts.Messages;

import NobleStack.Org.DataContracts.Accounts.ContactDetails;
import NobleStack.Org.DataContracts.Common.Application;

/**
 *
 * @author Ashu
 */
public class GetNotificationsRequest {
    public ContactDetails Receiver;
    public Application Application;
}
