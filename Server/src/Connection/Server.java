package Connection;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8000)) {
            System.out.println("** SERVER: Starting server");
            while (true) {
                Handler handler = new Handler(server);
                new Thread(() -> {
                    String request = handler.read_line();
                    System.out.println("REQ: " + request);
                    String response = "response";
                    try {Thread.sleep(2000);} catch (InterruptedException e) {};
                    System.out.println("RESP: " + response);
                    handler.write_line(response);
                    try { handler.close(); } catch (IOException e) {};
                }).start();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

