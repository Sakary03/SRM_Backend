package com.example.SRM_Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
public class SrmBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrmBackendApplication.class, args);
	}

}
