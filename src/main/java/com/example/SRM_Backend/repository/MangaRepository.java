package com.example.SRM_Backend.repository;

import com.example.SRM_Backend.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MangaRepository extends JpaRepository<Manga, Long> {
}
