package com.example.chatappjavafx.Controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordController {
    public static String hashPassword(String password) {
        String hashPassword = null;
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());
            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashPassword = s.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return hashPassword;
    }

    public static boolean verifyPassword(String password, String hashPassword) {
        if(hashPassword.equals(hashPassword(password)))
            return true;
        return false;
    }
}
