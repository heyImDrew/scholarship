package Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8070)) {
            System.out.println("** SERVER: Starting server");
            while (true) {
                Handler handler = new Handler(server);
                new Thread(() -> {
                    System.out.println("actions here");
                    boolean run = true;
                    while(run) {
                        try {
                            String action = (String)handler.read();
                            System.out.println(action);
                        }
                        catch (ClassNotFoundException | IOException e) {};
                    }
                    try { handler.close(); } catch (IOException e) {};
                }).start();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

