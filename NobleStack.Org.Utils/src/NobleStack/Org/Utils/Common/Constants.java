/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NobleStack.Org.Utils.Common;

/**
 *
 * @author Ashu
 */
public final class Constants {

    public static class Database {

        public static String url = "jdbc:mysql://localhost:3306/Noble_Stack";
        public static String driver = "com.mysql.jdbc.Driver";
        public static String userName = "root";
        public static String password = "ashu";
    }

    public static enum Status {

        User_Details("user_details"), IN_ACTIVE("In Active");

        private final String key;

        Status(String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return this.key;
        }

    }
}
