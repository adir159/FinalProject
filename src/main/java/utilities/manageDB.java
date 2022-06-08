package utilities;

import java.sql.DriverManager;
import java.sql.SQLException;

public class manageDB extends commonOps{

    public static void openConnection(String dbURL, String user, String password){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbURL,user,password);
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error found while connecting to DB,see details: " + e);
        }
    }
    public static void closeConnection(){
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Error while trying to close the connection,see details: " +e);
        }
    }
}
