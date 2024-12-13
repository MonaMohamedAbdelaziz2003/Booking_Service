package com.example.bookingserviceproject.services.authantication;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
// import org.springframework.context.annotation.Bean;
public class DES {
    private final SecretKey key;
    private Cipher encCipher;
    private Cipher decCipher;

    public DES() throws Exception {
        this.key = generateKey();
        initCiphers();
    }

    public DES(SecretKey key) throws Exception {
        this.key = key;
        initCiphers();
    }

    private void initCiphers() throws Exception {
        encCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        decCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        encCipher.init(Cipher.ENCRYPT_MODE, key);
        decCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(encCipher.getIV()));
    }


    public byte[] encrypt(String message) throws Exception {
        return encCipher.doFinal(message.getBytes());
    }

    public String decryt(byte[] messsage) throws Exception {
        return new String(decCipher.doFinal(messsage));
    }


    public static SecretKey generateKey() throws Exception {
        return KeyGenerator.getInstance("DES").generateKey();
    }
}