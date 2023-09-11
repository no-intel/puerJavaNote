package apiSecurity.signature;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class DSignature {
    private static final String KEY_FACTORY_ALGORITHM = "RSA";
    private static final String CHARSET = "UTF-8";
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        byte[] encodedPublicKey = publicKey.getEncoded();
        byte[] encodedPrivateKey = privateKey.getEncoded();

        System.out.println("키 원복 테스트");
        System.out.println("public = " + publicKey.equals(generatePublicKey(encodedPublicKey)));
        System.out.println("private = " + privateKey.equals(generatePrivateKey(encodedPrivateKey)));
        System.out.println("키 원복 테스트 끝");

        encryptAndDecrypt(encodedPrivateKey, encodedPublicKey);

        String word = "Hello World";
        String signature = sign(word, encodedPrivateKey);
        boolean verify = verify(word, signature, encodedPublicKey);
        System.out.println("전자서명 테스트 : " + verify);


    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_FACTORY_ALGORITHM);
        generator.initialize(512, new SecureRandom());
        return generator.generateKeyPair();
    }

    public static void encryptAndDecrypt(byte[] encodedPrivateKey, byte[] encodedPublicKey) throws Exception {
        String plainText = "Hello World";
        String cipherText = encrypt(plainText, encodedPublicKey);
        System.out.println("cipherText = " + cipherText);
        String decryptText = decrypt(cipherText, encodedPrivateKey);
        System.out.println("decryptText = " + decryptText);
        boolean equals = plainText.equals(decryptText);
        System.out.println("암복호화 테스트 : " + equals);
    }

    public static String encrypt(String plainText, byte[] encodedPublicKey) throws Exception {
        PublicKey publicKey = generatePublicKey(encodedPublicKey);
        Cipher cipher = Cipher.getInstance(KEY_FACTORY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(plainText.getBytes(CHARSET));
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decrypt(String cipherText, byte[] encodedPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
        PrivateKey privateKey = generatePrivateKey(encodedPrivateKey);
        byte[] bytes = Base64.getDecoder().decode(cipherText);
        Cipher cipher = Cipher.getInstance(KEY_FACTORY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(bytes), CHARSET);
    }

    public static String sign(String plainText, byte[] encodedPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException, SignatureException, InvalidKeyException {
        Signature privateSignature = Signature.getInstance(SIGNATURE_ALGORITHM);
        privateSignature.initSign(generatePrivateKey(encodedPrivateKey));
        privateSignature.update(plainText.getBytes(CHARSET));
        byte[] signature = privateSignature.sign();
        return Base64.getEncoder().encodeToString(signature);
    }

    public static boolean verify(String plainText, String signature, byte[] encodedPublicKey) throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        PublicKey publicKey = generatePublicKey(encodedPublicKey);
        return verifySignature(plainText, signature, publicKey);
    }

    // public key 원복
    private static PublicKey generatePublicKey(byte[] encodedPublicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_FACTORY_ALGORITHM);
        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedPublicKey));
    }

    // private key 원복
    private static PrivateKey generatePrivateKey(byte[] encodedPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_FACTORY_ALGORITHM);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedPrivateKey));
    }

    private static boolean verifySignature(String plainText, String signature, PublicKey publicKey) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initVerify(publicKey);
        sig.update(plainText.getBytes());
        if (!sig.verify(Base64.getDecoder().decode(signature))) {
            throw new RuntimeException("시그니쳐 불일치");
        }
        return true;
    }
}
