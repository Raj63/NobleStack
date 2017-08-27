/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hosts;

import Implementation.NotificationsBLC;
import NobleStack.Org.DataContracts.Messages.GetNotificationsRequest;
import NobleStack.Org.DataContracts.Messages.GetNotificationsResponseMessage;
import NobleStack.Org.DataContracts.Messages.SendNotificationRequest;
import NobleStack.Org.Utils.Common.Parser;
import NobleStack.Org.Utils.Common.Validator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Ashu
 */
@Path("notifications")
public class Notifications {
    @POST
    @Path("sendNotification")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response SendNotification(String content) throws Exception {
        Response response = null;
        try {
            if (Validator.IsNullOrWhiteSpace(content)) {
                throw new IllegalArgumentException("content");
            }
            SendNotificationRequest requestContent
                    = new Parser<SendNotificationRequest>().convertToObject(content, SendNotificationRequest.class);
            if (requestContent == null || !Validator.ValidateContact(requestContent.Sender)
                    || !Validator.ValidateContact(requestContent.Receiver) || requestContent.Message == null
                    || Validator.IsNullOrWhiteSpace(requestContent.Message.MessageText)) {
                throw new IllegalArgumentException();
            }
            NotificationsBLC notificationblc = new NotificationsBLC();
            notificationblc.SendNotifications(requestContent);
            response = Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
        return response;
    }
    
    @POST
    @Path("getNotifications")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetNotifications(String content) {
        Response response = null;
        try {
            if (Validator.IsNullOrWhiteSpace(content)) {
                 return Response.serverError().entity("content cannot be blank").build();
            }
            GetNotificationsRequest requestContent
                    = new Parser<GetNotificationsRequest>().convertToObject(content, GetNotificationsRequest.class);
            if (requestContent == null || !Validator.ValidateContact(requestContent.Receiver)
                    || requestContent.Application == null || Validator.IsNullOrWhiteSpace(requestContent.Application.ApplicationName)) {
                throw new IllegalArgumentException();
            }
             NotificationsBLC notificationblc = new NotificationsBLC();
            GetNotificationsResponseMessage result = notificationblc.GetNotification(requestContent);            
            response = Response.ok( new Parser<GetNotificationsResponseMessage>().convertToJson(result), MediaType.APPLICATION_JSON).build();
        } catch (Exception ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
             return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
        return response;
    }
}
