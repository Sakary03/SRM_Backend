package com.example.SRM_Backend.service;

import com.example.SRM_Backend.dto.ChapterResponseDTO;
import com.example.SRM_Backend.exception.MangaNotFoundException;
import com.example.SRM_Backend.model.Chapter;
import com.example.SRM_Backend.model.Manga;
import com.example.SRM_Backend.repository.ChapterRepository;
import com.example.SRM_Backend.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class ChapterService {

    @Autowired
    MangaRepository mangaRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ChapterRepository chapterRepository;

    public Chapter addNewChapter(ChapterResponseDTO chapterResponseDTO) {
        Optional<Manga> optionalManga = mangaRepository.findById(chapterResponseDTO.getBookid());
        if (optionalManga.isPresent()) {
            Manga manga = optionalManga.get();
            Chapter newChapter = new Chapter();
            newChapter.setChapterIndex(chapterResponseDTO.getChapterIndex());
            newChapter.setDateUpdate(chapterResponseDTO.getDateUpdate());
            newChapter.setNumPages(chapterResponseDTO.getPages().toArray().length);
            newChapter.setManga(manga);
            List<String> pages = new ArrayList<>();
            for (MultipartFile multipartFile : chapterResponseDTO.getPages()) {
                pages.add(cloudinaryService.upload(multipartFile).get("secure_url").toString());
            }
            newChapter.setPages(pages);
            return chapterRepository.save(newChapter);
        } else {
            throw new MangaNotFoundException(chapterResponseDTO.getBookid());
        }
    }

    public List<Chapter> makeFillTenChapter(List<Chapter> listChapter) {
        int i=0;
        while (listChapter.size()<10) {
            listChapter.add(listChapter.get(i));
            i++;
        }
        return listChapter;
    }
}
