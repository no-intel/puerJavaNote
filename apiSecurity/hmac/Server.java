package apiSecurity.hmac;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Server {
    private final SecretKey secretKey;

    public Server(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    void server() throws Exception {
        ServerSocket serverSocket = new ServerSocket(8083);
        Socket socket = serverSocket.accept();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream out = new PrintStream(socket.getOutputStream());

        out.println("Well Come 8083!");
        out.println("This is Hmac Server");
        out.println("Have a good Time");

        String line = null;
        Map<String, String> data = new HashMap<>();

        while ((line = reader.readLine()) != null) {
            if (line.equalsIgnoreCase("exit")) break;
            System.out.println(line);
            String[] split = line.split(":");
            data.put(split[0], split[1]);
        }
        if (this.isOk(data, secretKey)) {
            System.out.println(data.get("msg"));
        }
        reader.close();
        out.close();
        socket.close();
    }

    private boolean isOk(Map<String, String> data, SecretKey secretKey) throws Exception {
        String msg = data.get("msg");
        String hmac = data.get("hmac");

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] result = mac.doFinal(msg.getBytes());
        return Objects.equals(hmac, Arrays.toString(result));
    }
}
