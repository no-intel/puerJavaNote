package apiSecurity.hmac;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Hmac {
    public static void main(String[] args) throws Exception {
        // 공유할 키
        KeyGenerator key = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey = key.generateKey();

        // 서버 실행
        Thread serverThread = new Thread(() -> {
            try {
                new Server(secretKey).server();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // 클라 실행
        Thread clientThread = new Thread(() -> {
            try {
                Client client = new Client(secretKey);
                String msg = "Hello World";
                client.client(msg, client.toHmac(msg));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        serverThread.start();
        clientThread.start();
        serverThread.join();
        clientThread.join();
    }
}
