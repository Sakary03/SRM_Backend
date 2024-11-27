package com.example.SRM_Backend.service;

import com.example.SRM_Backend.dto.MangaDTO;
import com.example.SRM_Backend.model.Category;
import com.example.SRM_Backend.model.Manga;
import com.example.SRM_Backend.repository.CategoryRepository;
import com.example.SRM_Backend.repository.MangaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MangaService {
    private static final Logger log = LoggerFactory.getLogger(MangaService.class);
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private MangaRepository mangaRepository;
    @Autowired
    private CateService cateService;

    public Set<Category> addCategory(List<Long> categories) {
        Set<Category> cateSet=new HashSet<>();
        for (Long categoryID:categories) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryID);
            if (optionalCategory.isPresent()) {
                Category category = optionalCategory.get();
                cateSet.add(category);
            }
        }
        return cateSet;
    }
    public Manga addManga(MangaDTO mangaDTO) {
        Manga newManga=new Manga();
        newManga.setName(mangaDTO.getName());
        newManga.setPoster(mangaDTO.getPosterName());
        Set<Category> categories = new HashSet<>();
        for (Long categoryId : mangaDTO.getCategory()) {
            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            categoryOptional.ifPresent(categories::add);
        }
        newManga.setCategories(categories);
        return mangaRepository.save(newManga);
    }

    public List<Manga> getTopFive() {
        List<Manga> allManga=mangaRepository.findTop5ByOrderByBookID();
        System.out.println(">> Number of Manga return:" + allManga.size());
        if (allManga.size()==0) throw new RuntimeException("No Manga found");
        if (allManga.size()<5) {
            while (allManga.size()<5) {
                allManga.add(allManga.get(0));
            }
            return allManga;
        } else return allManga;
    }

    public String deleteManga(Long bookid) {
        Manga manga = mangaRepository.findById(bookid).orElse(null);
        if (manga==null) return "Manga not found";
        for (Category category: manga.getCategories()) {
            cateService.removeManga(category, manga);
        }
        mangaRepository.deleteById(bookid);
        return "Manga remove successfully";
    }
}
