package com.example.SRM_Backend.controller;


import com.example.SRM_Backend.model.Category;
import com.example.SRM_Backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    List<Category> listCategory() {
        return categoryRepository.findAll();
    }

    @PostMapping("/category")
    Category addCategory(@RequestParam("category_name") String category_name,
                         @RequestParam("overview") String overview) {
        Category newCategory = new Category();
        newCategory.setCategoryName(category_name);
        newCategory.setOverview(overview);
        return categoryRepository.save(newCategory);
    }
}
