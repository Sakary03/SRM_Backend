package com.example.SRM_Backend.controller;
import com.example.SRM_Backend.model.Category;
import com.example.SRM_Backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/get-all")
    List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getCateById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryRepository.findById(id), HttpStatus.OK);
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
