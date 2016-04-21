package com.github.alexgoncharov06.shared;


import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Aleksandr Honcharov (alexwolf) on 16.04.16.
 */
public class CryptPassword {
    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(CryptPassword.class.getName());
    private static String SOLD = "Ardas";

    public static String encryptPassword(String password) {

        String soldPassword = "";
        for (int i = 0; i < password.length(); i++) {
            soldPassword += String.valueOf(password.charAt(i));
            if (i < SOLD.length()) {
                soldPassword += String.valueOf(SOLD.charAt(i));
            }
        }
        MessageDigest crypt = null;
        try {
            crypt = MessageDigest.getInstance("SHA-256");
            crypt.reset();
            crypt.update(soldPassword.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            log.warning(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            log.warning(e.getMessage());
        }

        return new BigInteger(1, crypt.digest()).toString(16);
    }

}
