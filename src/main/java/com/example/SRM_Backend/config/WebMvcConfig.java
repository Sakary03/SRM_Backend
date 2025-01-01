package com.example.SRM_Backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:src/main/webapp/resources/images/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")   // Áp dụng cho tất cả các endpoint API
                .allowedOrigins("http://localhost:3000")  // Cho phép yêu cầu từ frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Cho phép các phương thức HTTP
                .allowedHeaders("*")  // Cho phép tất cả các header
                .allowCredentials(true);  // Cho phép cookie (nếu cần)
    }
}
