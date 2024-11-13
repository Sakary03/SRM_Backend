package com.example.SRM_Backend;

import com.example.SRM_Backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
@SpringBootApplication
public class SrmBackendApplication {

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

}
