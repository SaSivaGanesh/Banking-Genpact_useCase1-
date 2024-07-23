package bank.model;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class PasswordUtil {
    private static final String ALGORITHM = "AES";
    private static final String KEY = "1Hbfh667adfDEJ78"; // 16-byte key for AES-128

    // Method to check if the password is encrypted
    public static boolean isEncrypted(String password) {
        // Check if the password is base64 encoded and prefixed with "ENC("
        return password.startsWith("ENC(") && password.endsWith(")");
    }

    // Method to decrypt the encrypted password
    public static String decrypt(String encryptedPassword) {
        try {
            String base64EncryptedPassword = encryptedPassword.substring(4, encryptedPassword.length() - 1);
            byte[] decodedPassword = Base64.getDecoder().decode(base64EncryptedPassword);

            SecretKey secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decryptedBytes = cipher.doFinal(decodedPassword);
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting password", e);
        }
    }

    // Method to encrypt the password
    public static String encrypt(String password) {
        try {
            SecretKey secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            String base64EncryptedPassword = Base64.getEncoder().encodeToString(encryptedBytes);

            return "ENC(" + base64EncryptedPassword + ")";
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting password", e);
        }
    }
}
