package com.endava.tmd.BookProject.utils;

import com.endava.tmd.BookProject.config.PasswordConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class Utils {
    public static String encodedPassword(String plainPassword){
        return PasswordConfig.passwordEncoder().encode(plainPassword);
    }
}
