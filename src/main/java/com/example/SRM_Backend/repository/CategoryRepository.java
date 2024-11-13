package com.example.SRM_Backend.repository;

import com.example.SRM_Backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByMangas_BookID(Long bookId);
}
