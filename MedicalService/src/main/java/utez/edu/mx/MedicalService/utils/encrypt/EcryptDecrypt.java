package utez.edu.mx.MedicalService.utils.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Base64;

@Service
@Slf4j
public class EcryptDecrypt {
    @Value("${encryption.secret-key}")
    private String secretKey;

    public String decrypt(String data) {
        try {
            byte[] KeyData = secretKey.getBytes();
            SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
            byte[] ecryptedtexttobytes = Base64.getDecoder().
                    decode(data);
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, KS);
            byte[] decrypted = cipher.doFinal(ecryptedtexttobytes);
            String decryptedString =
                    new String(decrypted, Charset.forName("UTF-8"));
            return decryptedString;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public String encrypt(String data) {
        try {
            byte[] KeyData = secretKey.getBytes();
            SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, KS);
            String encryptedtext = Base64.getEncoder().
                    encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
            return encryptedtext;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
