package security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class UserVerifier {
    private final String decodedID;
    private static final String SYMMETRIC_SECRET_KEY = "mcMREavkBsiA3gYHxHSlOsvAVdNxjM6O";
    private static final String SECRET_KEYWORD = "TANGO";

    public UserVerifier(String encodedID){
        this.decodedID = decryptMessage(encodedID);
    }

    public boolean isEncodedIdValid() {
        if(decodedID == null){
            return false;
        }
        return decodedID.startsWith(SECRET_KEYWORD);
    }


    public  String getRealID() {
        if (decodedID.startsWith(SECRET_KEYWORD)) {
            return decodedID.substring(SECRET_KEYWORD.length());
        }
        return null;
    }

    private String decryptMessage(String ciphertext) {
        try {
            byte[] decodeSecretKey = Base64.getDecoder().decode(SYMMETRIC_SECRET_KEY);
            SecretKey secretKey = new SecretKeySpec(decodeSecretKey, 0, decodeSecretKey.length, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext.trim())));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
