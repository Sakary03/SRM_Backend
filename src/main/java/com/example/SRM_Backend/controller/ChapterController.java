package com.example.SRM_Backend.controller;


import com.example.SRM_Backend.dto.ChapterResponseDTO;
import com.example.SRM_Backend.model.Chapter;
import com.example.SRM_Backend.repository.MangaRepository;
import com.example.SRM_Backend.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("http://localhost:3000")
public class ChapterController {
    @Autowired
    MangaRepository mangaRepository;
    @Autowired
    private ChapterService chapterService;

    @PostMapping("/addchapter/{bookid}")
    public Chapter addChapter(@RequestParam("bookid") Long bookid,
                              @RequestParam("pages") List<MultipartFile> pages,
                              @RequestParam("chapterIndex") int ChapterIndex,
                              @RequestParam("dateUpdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dateUpdate) {
        ChapterResponseDTO chapterResponseDTO=new ChapterResponseDTO();
        chapterResponseDTO.setBookid(bookid);
        chapterResponseDTO.setPages(pages);
        chapterResponseDTO.setChapterIndex(ChapterIndex);
        chapterResponseDTO.setDateUpdate(dateUpdate);
        return chapterService.addNewChapter(chapterResponseDTO);
    }
}
