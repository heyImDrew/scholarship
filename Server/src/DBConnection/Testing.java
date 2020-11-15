package DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Testing {
    public static void main(String[] strings) throws ClassNotFoundException, SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        System.out.println("Connection to DB succeed!");
        Statement statement = connection.createStatement();
        System.out.println("Statement creation succeed!");
        connection.close();
        System.out.println("Connection closed!");
    }
}
