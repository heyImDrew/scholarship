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
                String query = "SELECT * FROM scholarship.Student WHERE login = ? AND password = ?";
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
                String query = "SELECT * FROM scholarship.Deanery WHERE login = ? AND password = ?";
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
                String query = "SELECT * FROM scholarship.Accountant WHERE login = ? AND password = ?";
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

            case "regAccountant": {
                String log = (String)handler.read();
                String pass = (String)handler.read();
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String count = "SELECT COUNT(*) FROM scholarship.Accountant";
                Statement statement = connection.createStatement();
                ResultSet count_acc_set = statement.executeQuery(count);
                count_acc_set.next();
                int next_id = count_acc_set.getInt("COUNT(*)");
                String query = "INSERT INTO scholarship.Accountant (idAccountant, name, lastName, patronymic, phone, login, password) VALUES (?, 'Anon', 'Anon', 'Anon', 'None', ?, ?)";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, next_id + 1);
                preparedStmt.setString(2, log);
                preparedStmt.setString(3, pass);
                preparedStmt.execute();
                connection.close();
            }
        }
    }
}
