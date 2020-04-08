package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtility {
    private static String DB_URL;
    private static  String USERNAME;
    private static String PASSWORD;

    private static Connection connection;

    public static void init(){
        DB_URL = PropertiesUtility.getProperty("DB_URL");
        USERNAME = PropertiesUtility.getProperty("USERNAME");
        PASSWORD = PropertiesUtility.getProperty("PASSWORD");
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        DB_URL="jdbc:mariadb://classdb2.csc.ncsu.edu:3306/nnathan";
        USERNAME="nnathan";
        PASSWORD="200323507";
        Class.forName("org.mariadb.jdbc.Driver");
        connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        return  connection;
    }

    public static void closeconnection() throws SQLException {
        if(connection!=null){
            connection.close();
            connection = null;
        }
    }
}
