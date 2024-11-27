package com.example.SRM_Backend.repository;

import com.example.SRM_Backend.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {

    // Custom method to find all mangas in a specific category
    List<Manga> findByCategories_CategoryID(Long categoryId);

    List<Manga> findTop5ByOrderByBookID();
}