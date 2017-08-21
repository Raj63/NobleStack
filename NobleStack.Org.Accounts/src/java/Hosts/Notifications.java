/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hosts;

import NobleStack.Org.DataContracts.Messages.SendNotificationRequest;
import NobleStack.Org.Utils.Common.Parser;
import NobleStack.Org.Utils.Common.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ashu
 */
@Path("notifications")
public class Notifications {
    @POST
    @Path("sendNotification")
    @Consumes(MediaType.APPLICATION_JSON)
    public void SendNotification(String content) {
        if (Validator.IsNullOrWhiteSpace(content)){
            throw new IllegalArgumentException("content");
        }
        SendNotificationRequest requestContent = 
                new Parser<SendNotificationRequest>().convert(content, SendNotificationRequest.class);
        if(requestContent == null || !Validator.ValidateContact(requestContent.Sender)|| 
                !Validator.ValidateContact(requestContent.Receiver) || requestContent.Message == null 
                || Validator.IsNullOrWhiteSpace(requestContent.Message.MessageText))    {
            throw new IllegalArgumentException();
        }
        
    }
}
