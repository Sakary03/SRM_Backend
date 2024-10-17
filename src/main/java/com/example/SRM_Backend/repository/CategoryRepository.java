package com.example.SRM_Backend.repository;

import com.example.SRM_Backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
