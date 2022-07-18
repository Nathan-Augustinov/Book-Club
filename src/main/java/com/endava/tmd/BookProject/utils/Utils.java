package com.endava.tmd.BookProject.utils;

import com.endava.tmd.BookProject.config.PasswordConfig;


public class Utils {
    public static String encodedPassword(String plainPassword){
        return PasswordConfig.passwordEncoder().encode(plainPassword);
    }
}
