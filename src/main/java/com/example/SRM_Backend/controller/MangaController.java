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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.PrivateKey;
import java.util.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/manga")
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

    @GetMapping("/")
    List<Manga> getAllMangas(){
        return mangaRepository.findAll();
    }

    @PostMapping("/create-manga")
    MangaDTO createManga(@RequestParam("name") String name,
                      @RequestParam("poster")MultipartFile poster,
                      @RequestParam("categoryList")List<String> categoriesList){
        System.out.println(categoriesList.size());
        Map<String, Object> newPoster = cloudinaryService.upload(poster);
        String posterName = newPoster.get("secure_url").toString();
        MangaDTO mangaDTO=new MangaDTO(name, posterName, categoriesList);
        mangaService.addManga(mangaDTO);
        return mangaDTO;
    }

    @GetMapping("/{bookid}")
    Manga getMangaByBookId(@PathVariable("bookid") Long bookid){
        return mangaRepository.findById(bookid).orElseThrow(()->new UserNotFoundException(bookid));
    }

    @GetMapping("/get-top-5")
    List<Manga> getTopFive() {
        return mangaService.getTopFive();
    }

    @DeleteMapping("/delete/{bookid}")
    ResponseEntity<?> deleteManga(@RequestParam("bookid") Long bookid) {
        return new ResponseEntity<>(mangaService.deleteManga(bookid), HttpStatus.OK);
    }
}
