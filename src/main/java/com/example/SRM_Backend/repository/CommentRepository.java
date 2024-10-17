package com.example.SRM_Backend.repository;

import com.example.SRM_Backend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
