package com.example.SRM_Backend.repository;
import com.example.SRM_Backend.model.Category;
import com.example.SRM_Backend.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername (String username);
}
