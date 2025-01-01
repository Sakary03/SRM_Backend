package com.example.SRM_Backend.repository;

import com.example.SRM_Backend.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    public Chapter findChapterByChapterID(Long chapterID);

    List<Chapter> findTop10ByOrderByDateUpdateDesc();
}
