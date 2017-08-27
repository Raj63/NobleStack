/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NobleStack.Org.DataAccess;

import NobleStack.Org.DataContracts.Accounts.ContactDetails;
import NobleStack.Org.DataContracts.Common.Application;
import NobleStack.Org.DataContracts.Messages.GetNotificationsResponse;
import NobleStack.Org.DataContracts.Messages.GetNotificationsResponseMessage;
import NobleStack.Org.DataContracts.Messages.Message;
import NobleStack.Org.Utils.Common.Validator;
import NobleStack.Org.Utils.DataAccess.ConnectionHelper;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ashu
 */
public class NotificationsDALC {

    /**
     *
     * @param message
     * @param senderUserId
     * @param receiverUserId
     * @param applicationId
     * @throws SQLException
     */
    public void SaveMessageDetails(Message message, String senderUserId, String receiverUserId, String applicationId) throws Exception {
        if (Validator.IsNullOrWhiteSpace(senderUserId) || Validator.IsNullOrWhiteSpace(receiverUserId)
                || message == null || Validator.IsNullOrWhiteSpace(message.MessageText)) {
            throw new IllegalArgumentException();
        }

        Connection conn = ConnectionHelper.getConnection();
        try {
            CallableStatement stmt = conn.prepareCall("{call pc_message_queue_i(?,?,?,?)}");
            stmt.setString("msg_txt", message.MessageText);
            stmt.setString("sender", senderUserId);
            stmt.setString("receiver", receiverUserId);
            stmt.setString("app_id",applicationId);
            stmt.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param userId
     * @param applicationId
     * @return
     * @throws java.sql.SQLException
     */
    public GetNotificationsResponseMessage GetNotifications(String userId, String applicationId) throws Exception {
        GetNotificationsResponseMessage respMessage = null;
        if (Validator.IsNullOrWhiteSpace(userId)) {
            throw new IllegalArgumentException();
        }

        Connection conn = ConnectionHelper.getConnection();
        try {
            CallableStatement stmt = conn.prepareCall("{call pc_get_notifications(?,?)}");
            stmt.setInt("userid", Integer.parseInt(userId));
            stmt.setInt("applicationid",Integer.parseInt(applicationId));
            ResultSet rs = stmt.executeQuery();

            respMessage = new GetNotificationsResponseMessage();
            respMessage.response = new ArrayList<>();
            GetNotificationsResponse notif = null;
            while (rs.next()) {
                notif = new GetNotificationsResponse();
                notif.Messages = new Message();
                notif.Messages.MessageId = rs.getInt("mq_id");
                notif.Messages.MessageText = rs.getString("mq_description");
                notif.sender = new ContactDetails();
                notif.sender.EmailAddress = rs.getString("email_id");
                notif.sender.PhoneNumber = rs.getString("phone_number");
                notif.Application = new Application();
                notif.Application.ApplicationId = rs.getString("application_id");
                respMessage.response.add(notif);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respMessage;
    }
}
