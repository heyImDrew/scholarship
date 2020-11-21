package Connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Handler implements Closeable {
    private final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public Handler(String ip, int port) {
        try {
            this.socket = new Socket(ip, port);
            this.reader = create_reader();
            this.writer = create_writer();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public Handler(ServerSocket server) {
        try {
            this.socket = server.accept();
            this.reader = create_reader();
            this.writer = create_writer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void write_line(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String read_line() {
        try {
            return reader.readLine();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private BufferedReader create_reader() throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    private BufferedWriter create_writer() throws IOException {
        return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void close() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }
}
