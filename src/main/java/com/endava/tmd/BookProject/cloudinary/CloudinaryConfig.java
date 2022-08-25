package com.endava.tmd.BookProject.cloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", "djfd47dbw");
        config.put("api_key", "771451286342873");
        config.put("api_secret", "vCWE1_-An8yw0x1NhIRex4MINL4");
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }
}
