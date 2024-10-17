package com.example.SRM_Backend.repository;
import com.example.SRM_Backend.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
