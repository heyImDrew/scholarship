package Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8060)) {
            System.out.println("** SERVER: Starting server");
            while (true) {
                Handler handler = new Handler(server);
                new Thread(() -> {
                    boolean run = true;
                    while(run) {
                        try {
                            Object action = (Object)handler.read();

                            // MAKE ACTION CLASS INSTEAD OF "IF" CLAUSE
                            if (action.equals("enterStudent")) {
                                System.out.println("student login");
                            }
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

