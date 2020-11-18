package Connection;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class Client {
    public static void main(String[] arg) {
        try {
            Socket clientSocket = new Socket("127.0.0.1",2525);
            System.out.println("** Connection with server established....");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            ObjectOutputStream coos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream cois = new ObjectInputStream(clientSocket.getInputStream());
            String clientMessage = new String("");
            String serverMessage;
            while(!clientMessage.equals("0")) {
                System.out.print("\tMenu:\n1. -\n2. -\n3. -\n0. -\n\t\tChoice: ");
                clientMessage = stdin.readLine();
                switch (Integer.valueOf(clientMessage)) {
                    case 1:
                    case 2:
                    case 3:
                        coos.writeObject(clientMessage);
                        serverMessage = (String)cois.readObject();
                        System.out.println(serverMessage);
                        break;
                    case 0:
                        coos.writeObject(clientMessage);
                        coos.close();
                        cois.close();
                        clientSocket.close();
                        break;
                    default:
                        break;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}