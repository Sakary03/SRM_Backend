package com.example.SRM_Backend.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    private final String CLOUD_NAME = "deg6p9a9o";
    private final String API_KEY = "959358521999763";
    private final String API_SECRET = "FkxnswfvEGhSVgkk8BsUlTcMQBk";

    @Bean
    public Cloudinary getCloudinary(){
        Map config = new HashMap();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);
        config.put("secure", true);
        return new Cloudinary(config);
    }
}
