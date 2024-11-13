package com.example.SRM_Backend.service;

import com.example.SRM_Backend.dto.MangaDTO;
import com.example.SRM_Backend.model.Category;
import com.example.SRM_Backend.model.Manga;
import com.example.SRM_Backend.repository.CategoryRepository;
import com.example.SRM_Backend.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MangaService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private MangaRepository mangaRepository;

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
}
