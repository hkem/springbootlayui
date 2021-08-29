package com.example.demo.common;


//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

public class PassEncryption {

    //admin加密
    public static String adminencryptionpass(String pass){

        BCryptPasswordEncoder textEncryptor = new BCryptPasswordEncoder();
        String newPassword = textEncryptor.encode(pass);
        return newPassword;

    }

    //admin解密
    public static boolean admindecryptpass(String pass,String encodepass){
        BCryptPasswordEncoder textEncryptor = new BCryptPasswordEncoder();
        boolean passbool = textEncryptor.matches(pass,encodepass);
        return passbool;
    }
}
