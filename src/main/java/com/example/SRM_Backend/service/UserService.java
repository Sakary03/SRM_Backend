package com.example.SRM_Backend.service;

import com.example.SRM_Backend.dto.request.RegisterUserDTO;
import com.example.SRM_Backend.dto.request.UserRequest;
import com.example.SRM_Backend.model.Role;
import com.example.SRM_Backend.model.User;
import com.example.SRM_Backend.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

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

    public User registerUser(RegisterUserDTO registerUserDTO) throws MessagingException {
        User newUser=new User();
        newUser.setUsername(registerUserDTO.getUsername());
        newUser.setName(registerUserDTO.getName());
        newUser.setEmail(registerUserDTO.getEmail());
        newUser.setDob(registerUserDTO.getDate());
        newUser.setAddress(registerUserDTO.getAddress());
        newUser.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        if (registerUserDTO.getRole().equals("USER")) newUser.setRole(Role.USER);
        else newUser.setRole(Role.ADMIN);
        newUser.setAvatar(registerUserDTO.getAvatar());
        emailService.registerCompleteEmail(newUser);
        return userRepository.save(newUser);
    }
}
