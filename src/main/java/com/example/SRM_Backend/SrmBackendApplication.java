package com.example.SRM_Backend;

import com.example.SRM_Backend.repository.*;
import com.example.SRM_Backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
@SpringBootApplication
public class SrmBackendApplication {
	@Autowired
	private EmailService emailService;
	@Autowired
	public UserRepository userRepository;
	@Autowired
	public MangaRepository mangaRepository;;
	@Autowired
	public CategoryRepository categoryRepository;
	@Autowired
	public CommentRepository commentRepository;
	@Autowired
	public ChapterRepository chapterRepository;
	public static void main(String[] args) {
		SpringApplication.run(SrmBackendApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("*");
			}
		};
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void sendEmail() {
//		emailService.sendEmail("huykhoa2k3@gmail.com", "Demo send email", "Demo send email [Body]");
//	}
}
