package com.example.SRM_Backend.controller;


import com.example.SRM_Backend.model.Manga;
import com.example.SRM_Backend.repository.CategoryRepository;
import com.example.SRM_Backend.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class MangaController {
    @Autowired
    private MangaRepository mangaRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/mangas")
    List<Manga> getAllMangas(){
        return mangaRepository.findAll();
    }

    @PostMapping("/manga")
    Manga createManga(@RequestBody Manga manga){
        return mangaRepository.save(manga);
    }
}
