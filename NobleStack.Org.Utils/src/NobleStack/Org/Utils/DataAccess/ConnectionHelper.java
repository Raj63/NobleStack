/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NobleStack.Org.Utils.DataAccess;

import NobleStack.Org.Utils.Common.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rajesh
 */
public class ConnectionHelper {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(Constants.Database.driver);
            conn = DriverManager.getConnection(Constants.Database.url, 
                    Constants.Database.userName, Constants.Database.password);
            System.out.println("Connected to the database");
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
}
