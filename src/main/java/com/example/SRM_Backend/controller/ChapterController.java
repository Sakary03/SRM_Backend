package com.example.SRM_Backend.controller;


import com.example.SRM_Backend.dto.ChapterResponseDTO;
import com.example.SRM_Backend.model.Chapter;
import com.example.SRM_Backend.repository.ChapterRepository;
import com.example.SRM_Backend.repository.MangaRepository;
import com.example.SRM_Backend.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.ZoneId;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/chapter")
public class ChapterController {
    @Autowired
    MangaRepository mangaRepository;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private ChapterRepository chapterRepository;

    @PostMapping("/addchapter/{bookid}")
    public Chapter addChapter(@RequestParam("bookid") Long bookid,
                              @RequestParam("pages") List<MultipartFile> pages,
                              @RequestParam("chapterIndex") int ChapterIndex) {
        ChapterResponseDTO chapterResponseDTO=new ChapterResponseDTO();
        chapterResponseDTO.setBookid(bookid);
        chapterResponseDTO.setPages(pages);
        chapterResponseDTO.setChapterIndex(ChapterIndex);
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        chapterResponseDTO.setDateUpdate(date);
        return chapterService.addNewChapter(chapterResponseDTO);
    }

    @GetMapping("/get-chapter/{chapterid}")
    public ResponseEntity<?> getChapter(@PathVariable("chapterid") Long chapterid) {
        Optional<Chapter> resChapter= Optional.of(chapterRepository.findChapterByChapterID(chapterid));
        if (resChapter.isPresent()) {
            return new ResponseEntity<>(resChapter, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Chapter not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-10-latest")
    public ResponseEntity<?> getTopLatestChapters() {
        List<Chapter> listLatestChapter=chapterRepository.findTop10ByOrderByDateUpdateDesc();
        if (listLatestChapter.isEmpty()) {
            return new ResponseEntity<>("No latest chapters found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(chapterService.makeFillTenChapter(listLatestChapter), HttpStatus.OK);
        }
    }

}
