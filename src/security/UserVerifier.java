package security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Questa classe gestisce la verifica dell'utente e la decodifica dell'ID utente.
 * Utilizza una chiave segreta simmetrica per decodificare un ID codificato e verificare
 * se l'ID contiene una parola chiave segreta.
 */
public class UserVerifier {
    // L'ID decodificato
    private final String decodedID;

    // Chiave segreta simmetrica per la decodifica
    private static final String SYMMETRIC_SECRET_KEY = "mcMREavkBsiA3gYHxHSlOsvAVdNxjM6O";

    // Parola chiave segreta per la verifica
    private static final String SECRET_KEYWORD = "TANGO";

    /**
     * Costruttore che decodifica l'ID codificato all'inizializzazione.
     *
     * @param encodedID L'ID utente codificato da decodificare.
     */
    public UserVerifier(String encodedID){
        this.decodedID = decryptMessage(encodedID);
    }

    /**
     * Verifica se l'ID decodificato è valido.
     *
     * @return True se l'ID contiene la parola chiave segreta, altrimenti False.
     */
    public boolean isEncodedIdValid() {
        if(decodedID == null){
            return false;
        }
        return decodedID.startsWith(SECRET_KEYWORD);
    }

    /**
     * Ottiene l'ID utente reale dopo la rimozione della parola chiave segreta.
     *
     * @return L'ID utente reale o null se l'ID non è valido.
     */
    public String getRealID() {
        if (decodedID.startsWith(SECRET_KEYWORD)) {
            return decodedID.substring(SECRET_KEYWORD.length());
        }
        return null;
    }

    /** Metodo privato per decodificare un messaggio utilizzando la chiave simmetrica
     *
     * @param ciphertext Testo criptato da decifrare
     * @return Il testo decifrato usando la chiave simmetrica
     */
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
