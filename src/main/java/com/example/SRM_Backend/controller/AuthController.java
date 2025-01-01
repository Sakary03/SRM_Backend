package com.example.SRM_Backend.controller;


import com.example.SRM_Backend.dto.LoginDTO;
import com.example.SRM_Backend.dto.request.RegisterUserDTO;
import com.example.SRM_Backend.dto.response.LoginResponseDTO;
import com.example.SRM_Backend.repository.UserRepository;
import com.example.SRM_Backend.security.SecurityService;
import com.example.SRM_Backend.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    SecurityService securityUtil;
    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO loginDto) {
        // Nạp input gồm username/password vào Security
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword());

        // xác thực người dùng => cần viết hàm loadUserByUsername
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // create a token
        String access_token = this.securityUtil.createToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LoginResponseDTO res = new LoginResponseDTO();
        res.setAccessToken(access_token);
        res.setUserInfo(userService.getUserByUsername(loginDto.getUsername()));
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDTO registerUserDTO) throws MessagingException {
        boolean isEmailExist = this.userService.isEmailExist(registerUserDTO.getEmail());
        boolean isUsernameExist=this.userService.isUsernameExist(registerUserDTO.getUsername());
        if (!isEmailExist && !isUsernameExist) {
            return ResponseEntity.ok().body(userService.registerUser(registerUserDTO));
        } else {
            return ResponseEntity.badRequest().body("Email hoặc tên người dùng đã tồn tại");
        }
    }
}
