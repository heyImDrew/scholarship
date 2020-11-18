package Connection;

import DBConnection.ConnectionClass;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Server {
    public static void main(String[] arg) throws ClassNotFoundException, SQLException {

        // CONNECTION TO DATABASE
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        System.out.println("** DB: Connection to DB succeed!");

        // SERVER-CLIENT CONNECTION
        ServerSocket serverSocket = null;
        Socket clientAccepted = null;
        ObjectInputStream sois = null;
        ObjectOutputStream soos = null;
        try {
            System.out.println("** SERVER: Server starting..");
            serverSocket = new ServerSocket(2525);
            clientAccepted = serverSocket.accept();
            System.out.println("** SERVER: Connection established..");
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());
            sois = new ObjectInputStream(clientAccepted.getInputStream());
            String clientMessageRecieved = new String("");
            boolean cycle = true;
            while(cycle)
            {
                clientMessageRecieved = (String)sois.readObject();
                System.out.println("** SERVER: Message recieved: '" + clientMessageRecieved + "'");
                switch (clientMessageRecieved) {
                    case "1":
                        soos.writeObject("** SERVER: First choice");
                        break;
                    case "2":
                        soos.writeObject("** SERVER: Second choice");
                        break;
                    case "3":
                        soos.writeObject("** SERVER: Third choice");
                        break;
                    case "0":
                        connection.close();
                        System.out.println("** DB: Connection closed!");
                        cycle = false;
                        break;
                    default:
                        break;
                }
            }
        }
        catch(Exception e) {}
        finally {
            try {
                sois.close();
                soos.close();
                clientAccepted.close();
                serverSocket.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}