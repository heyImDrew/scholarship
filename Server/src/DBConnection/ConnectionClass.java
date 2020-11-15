package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionClass {
    public Connection connection;
    public Connection getConnection() throws ClassNotFoundException {
        String URL = "jdbc:mysql://localhost:3306/scholarship?useSSL=false&serverTimezone=UTC";
        String USERNAME = "admin";
        String PASSWORD = "admin";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return connection;
    }
}