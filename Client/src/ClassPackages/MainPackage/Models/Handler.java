package ClassPackages.MainPackage.Models;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Handler implements Closeable {
    private final Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public Handler(String ip, int port) {
        try {
            this.socket = new Socket(ip, port);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public Handler(ServerSocket server) {
        try {
            this.socket = server.accept();
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(Object object) throws IOException {
        out.flush();
        out.writeObject(object);
    }

    public Object read() throws ClassNotFoundException, IOException {
        return in.readObject();
    }

    @Override
    public void close() throws IOException {
        socket.close();
        out.close();
        in.close();
    }
}
