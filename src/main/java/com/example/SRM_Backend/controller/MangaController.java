package com.example.SRM_Backend.controller;


import com.example.SRM_Backend.dto.MangaDTO;
import com.example.SRM_Backend.exception.UserNotFoundException;
import com.example.SRM_Backend.model.Category;
import com.example.SRM_Backend.model.Manga;
import com.example.SRM_Backend.model.User;
import com.example.SRM_Backend.repository.CategoryRepository;
import com.example.SRM_Backend.repository.MangaRepository;
import com.example.SRM_Backend.service.CloudinaryService;
import com.example.SRM_Backend.service.ImageService;
import com.example.SRM_Backend.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.PrivateKey;
import java.util.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class MangaController {
    @Autowired
    private MangaRepository mangaRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private MangaService mangaService;
    String manga_upload_dir="src/main/webapp/resources/images/MangaPoster/";
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/mangas")
    List<Manga> getAllMangas(){
        return mangaRepository.findAll();
    }

    @PostMapping("/manga")
    MangaDTO createManga(@RequestParam("name") String name,
                      @RequestParam("poster")MultipartFile poster,
                      @RequestParam("categoryList")List<String> categoriesList){
        System.out.println(categoriesList.size());
        String posterName= cloudinaryService.upload(poster).get("secure_url").toString();
        MangaDTO mangaDTO=new MangaDTO(name, posterName, categoriesList);
        mangaService.addManga(mangaDTO);
        return mangaDTO;
    }

    @GetMapping("/manga/{bookid}")
    Manga getMangaByBookId(@PathVariable("bookid") Long bookid){
        return mangaRepository.findById(bookid).orElseThrow(()->new UserNotFoundException(bookid));
    }

}
