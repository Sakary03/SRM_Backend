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

    public User saveUser(UserRequest userRequest) {
        User newUser=new User();
        newUser.setName(userRequest.getName());
        newUser.setUsername(userRequest.getUsername());
        newUser.setDob(userRequest.getDob());
        newUser.setEmail(userRequest.getEmail());
        newUser.setAddress(userRequest.getAddress());
        newUser.setRole(userRequest.getRole());
        newUser.setPassword(userRequest.getPassword());
        newUser.setAvatar(userRequest.getAvatar());
        return userRepository.save(newUser);
    }

    public User getUserByUsername (String username) {
        return userRepository.findByUsername(username);
    }
}
