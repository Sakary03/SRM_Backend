package com.example.SRM_Backend.controller;


import com.example.SRM_Backend.model.Category;
import com.example.SRM_Backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("/create-category")
    Category addCategory(@RequestParam("category_name") String category_name,
                         @RequestParam("overview") String overview) {
        Category newCategory = new Category();
        newCategory.setCategoryName(category_name);
        newCategory.setOverview(overview);
        return categoryRepository.save(newCategory);
    }
}
