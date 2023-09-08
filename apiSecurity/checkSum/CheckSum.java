package apiSecurity.checkSum;

import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class CheckSum {
    public static void main(String[] args) throws Exception {
        //https://mkyong.com/java/how-to-generate-a-file-checksum-value-in-java/

        String filepath = "apiSecurity/checkSum/test.txt";
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String hex = checksum(filepath, md);
        System.out.println(hex);

        //SHA-256 계산기로 구한 파일 체크섬 : 0d00febeff3ccf9c8323e24f7a5a7d8aa05d4dfd4ada1a7dbe4f22addbde00ad
        System.out.println("0d00febeff3ccf9c8323e24f7a5a7d8aa05d4dfd4ada1a7dbe4f22addbde00ad".equals(hex));
    }

    private static String checksum(String filepath, MessageDigest md) throws Exception {
        DigestInputStream dis = new DigestInputStream(new FileInputStream(filepath), md);

        while (dis.read() != -1) {
            md = dis.getMessageDigest();
        }

        StringBuilder result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
