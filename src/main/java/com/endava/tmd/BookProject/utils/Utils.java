package com.endava.tmd.BookProject.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class Utils {
    public static String encodedPassword(String plainPassword){
        int strength = 10;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        return encoder.encode(plainPassword);
    }
}
