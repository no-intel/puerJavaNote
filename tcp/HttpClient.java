package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class HttpClient {
    public static void main(String[] args) {
        try {
            new HttpClient().client();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void client() throws IOException {
        Socket socket = new Socket("localhost", 8083);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream out = new PrintStream(socket.getOutputStream());

        out.println("GET / HTTP/1.1");

        out.println("Host: http://localhost");
        out.println("User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0)"
                + " AppleWebKit/537.36 (KHTML, like Gecko)"
                + " Chrome/30.0.1599.101 Safari/537.36");

        out.println();

        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        out.close();
        socket.close();
    }
}
