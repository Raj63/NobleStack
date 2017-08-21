/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NobleStack.Org.DataAccess;

import NobleStack.Org.DataContracts.Messages.Message;
import NobleStack.Org.Utils.Common.Validator;
import NobleStack.Org.Utils.DataAccess.ConnectionHelper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Ashu
 */
public class NotificationsDALC {
    public void SaveMessageDetails(Message message, String senderUserId, String receiverUserId) throws SQLException{
       if (Validator.IsNullOrWhiteSpace(senderUserId) || Validator.IsNullOrWhiteSpace(receiverUserId) ||
               message == null || Validator.IsNullOrWhiteSpace(message.MessageText)) {
            throw new IllegalArgumentException();
        }
       
        try {
            Connection conn = ConnectionHelper.getConnection();
            CallableStatement stmt = conn.prepareCall("{call pc_message_queue_i(?,?,?)}");
            stmt.setString("msg_txt", message.MessageText);
            stmt.setString("sender", senderUserId);
            stmt.setString("receiver", receiverUserId);
            stmt.execute();
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
