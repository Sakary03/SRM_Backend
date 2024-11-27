package com.example.SRM_Backend.service;

import com.example.SRM_Backend.model.Category;
import com.example.SRM_Backend.model.Manga;
import com.example.SRM_Backend.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CateService {
    @Autowired
    CategoryRepository categoryRepository;
    @Transactional
    public Category updateManga(Manga manga,Long categoryID) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryID);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.addManga(manga);
            return categoryRepository.save(category);
        } else {
            throw new RuntimeException("Category with ID " + categoryID + " not found");
        }
    }

    public Category removeManga(Category category, Manga manga) {
        category.removeManga(manga);
        return category;
    }
}
