package com.example.SRM_Backend.service;

import com.example.SRM_Backend.dto.request.UserRequest;
import com.example.SRM_Backend.model.User;
import com.example.SRM_Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public User createUser(UserRequest userRequest) {
        User newUser=new User(userRequest);
        return userRepository.save(newUser);
    }

    public User getUserByUsername (String username) {
        return userRepository.findByUsername(username);
    }

    public Boolean isUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }
    public Boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }
}
