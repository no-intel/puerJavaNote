package apiSecurity.hmac;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Client {
    private final SecretKey secretKey;

    public Client(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    byte[] toHmac(String msg) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);

        return mac.doFinal(msg.getBytes());
    }

    void client(String msg, byte[] hmac) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8083);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream out = new PrintStream(socket.getOutputStream());

        out.println("msg:"+msg);
        out.println("hmac:"+Arrays.toString(hmac));
        out.println("exit");

        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();
        out.close();
        socket.close();
    }
}
