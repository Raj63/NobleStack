/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementation;

import NobleStack.Org.DataAccess.NotificationsDALC;
import NobleStack.Org.DataAccess.RegisterUserDALC;
import NobleStack.Org.DataContracts.Messages.SendNotificationRequest;

/**
 *
 * @author Ashu
 */
public class NotificationsBLC {

    public void SendNotifications(SendNotificationRequest request) throws Exception {
        try {
            RegisterUserDALC registerDalc = new RegisterUserDALC();
            String senderUserId = registerDalc.GetUserIdForApplication(request.Sender, request.Application);
            String receiverUserId = registerDalc.GetUserIdForApplication(request.Receiver, request.Application);
            NotificationsDALC notificationDalc = new NotificationsDALC();
            notificationDalc.SaveMessageDetails(request.Message, senderUserId, receiverUserId);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
