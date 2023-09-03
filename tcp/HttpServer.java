package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) {
        try {
            new HttpServer().server();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8083);

        while (true){
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());

            out.println("Well Come 8083!");
            out.println("This is Server");
            out.println("Have a good Time");

            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
            out.close();
            socket.close();
        }
    }
}
