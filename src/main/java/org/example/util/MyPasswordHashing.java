package org.example.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyPasswordHashing implements PasswordHashing {
    @Override
    public String doHashing(String email, String password) {
        String emailAndPass = email + password;
        String hash = pwdHash(emailAndPass);
        return pwdHash(hash);
    }

    private String pwdHash(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.update(password.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();

            for (byte b : digest) {
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
