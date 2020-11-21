package Connection;

import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        try (Handler handler = new Handler("127.0.0.1", 8000)) {
            System.out.println("** CLIENT: Connected to server");
            String request = "request text";
            System.out.println("** REQUEST: '" + request + "'");
            handler.write_line(request);
            String response = handler.read_line();
            System.out.println("** RESPONCE: '" + response + "'");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
