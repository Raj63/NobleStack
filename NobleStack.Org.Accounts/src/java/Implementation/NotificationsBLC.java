/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementation;

import NobleStack.Org.DataAccess.NotificationsDALC;
import NobleStack.Org.DataAccess.RegisterUserDALC;
import NobleStack.Org.DataContracts.Messages.GetNotificationsRequest;
import NobleStack.Org.DataContracts.Messages.GetNotificationsResponseMessage;
import NobleStack.Org.DataContracts.Messages.SendNotificationRequest;
import NobleStack.Org.Utils.Common.Validator;

/**
 *
 * @author Ashu
 */
public class NotificationsBLC {

    public void SendNotifications(SendNotificationRequest request) throws Exception {
        try {
            RegisterUserDALC registerDalc = new RegisterUserDALC();
            String senderUserId = registerDalc.GetUserId(request.Sender);
            String receiverUserId = registerDalc.GetUserId(request.Receiver);
            String applicationId = registerDalc.GetApplicationId(request.Application);
            if(Validator.IsNullOrWhiteSpace(senderUserId) || Validator.IsNullOrWhiteSpace(receiverUserId)||
                    senderUserId.equals("0")||receiverUserId.equals("0")){
                throw new Exception("User doesnt exist");
            }
            NotificationsDALC notificationDalc = new NotificationsDALC();
            notificationDalc.SaveMessageDetails(request.Message, senderUserId, receiverUserId,applicationId);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public GetNotificationsResponseMessage GetNotification(GetNotificationsRequest request) throws Exception{
        GetNotificationsResponseMessage  response = null;
        try{
            RegisterUserDALC registerDalc = new RegisterUserDALC();
            String receiverUserId = registerDalc.GetUserId(request.Receiver);
            String applicationId = registerDalc.GetApplicationId(request.Application);
            NotificationsDALC notificationDalc = new NotificationsDALC();
            response = notificationDalc.GetNotifications(receiverUserId, applicationId);
        }
        catch(Exception ex){
            throw ex;
        }
        return response;
    }
            
}
