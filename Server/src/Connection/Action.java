package Connection;

import DBConnection.ConnectionClass;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;

public class Action {
    public void execute(Handler handler, String action) throws IOException, ClassNotFoundException, SQLException {
        switch (action) {
            case "enterStudent": {
                String log = (String)handler.read();
                String pass = (String)handler.read();
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String query = "SELECT * FROM scholarship.student WHERE login = ? AND password = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, log);
                preparedStmt.setString(2, pass);
                ResultSet data = preparedStmt.executeQuery();
                if(data.next()) {
                    handler.write(String.valueOf(data.getInt("idStudent")));
                }
                else {
                    handler.write("Nothing");
                }
                connection.close();
                break;
            }
            case "enterDean": {
                String log = (String)handler.read();
                String pass = (String)handler.read();
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String query = "SELECT * FROM scholarship.deanery WHERE login = ? AND password = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, log);
                preparedStmt.setString(2, pass);
                ResultSet data = preparedStmt.executeQuery();
                if(data.next()) {
                    handler.write(String.valueOf(data.getInt("idDeanery")));
                }
                else {
                    handler.write("Nothing");
                }
                connection.close();
                break;
            }
            case "enterAccountant": {
                String log = (String)handler.read();
                String pass = (String)handler.read();
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String query = "SELECT * FROM scholarship.accountant WHERE login = ? AND password = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, log);
                preparedStmt.setString(2, pass);
                ResultSet data = preparedStmt.executeQuery();
                if(data.next()) {
                    handler.write(String.valueOf(data.getInt("idAccountant")));
                }
                else {
                    handler.write("Nothing");
                }
                connection.close();
                break;
            }
        }
    }
}
