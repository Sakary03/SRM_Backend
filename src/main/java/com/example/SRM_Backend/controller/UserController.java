package com.example.SRM_Backend.controller;
import com.example.SRM_Backend.dto.request.UserRequest;
import com.example.SRM_Backend.exception.UserNotFoundException;
import com.example.SRM_Backend.model.User;
import com.example.SRM_Backend.repository.UserRepository;
import com.example.SRM_Backend.service.CloudinaryService;
import com.example.SRM_Backend.service.ImageService;
import com.example.SRM_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    private static final String user_upload_dir="src/main/webapp/resources/images/UserAvatar/";
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CloudinaryService   cloudinaryService;

    @PostMapping("/user")
    public ResponseEntity<User> newUser(@RequestParam("name") String name,
                                  @RequestParam("username") String username,
                                  @RequestParam("email") String email,
                                  @RequestParam("dob") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob,
                                  @RequestParam("address") String address,
                                  @RequestParam("password") String password,
                                  @RequestParam("role") String role,
                                  @RequestParam("avatar") MultipartFile avatar) {
        Map<String, Object> avaData = cloudinaryService.upload(avatar);
        String avatarUrl = avaData.get("secure_url").toString();

        User newUser=new User();
        newUser.setName(name);
        newUser.setUsername(username);
        newUser.setDob(dob);
        newUser.setEmail(email);
        newUser.setAddress(address);
        newUser.setRole(role);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setAvatar(avatarUrl);
        return new ResponseEntity<>(userRepository.save(newUser), HttpStatus.OK);
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestParam("name") String name,
                    @RequestParam("username") String username,
                    @RequestParam("email") String email,
                    @RequestParam("dob") Date dob,
                    @RequestParam("address") String address,
                    @RequestParam("avatar") MultipartFile avatar,
                    @PathVariable Long id) {
        return userRepository.findById(id).map(user->{
            user.setUsername(username);
            user.setName(name);
            user.setEmail(email);
            try {
                cloudinaryService.delete(cloudinaryService.publicIdExtractor(user.getAvatar()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String avatarName= cloudinaryService.upload(avatar).get("secure_url").toString();
            user.setAvatar(avatarName);
            return userRepository.save(user);
        }).orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) throws IOException {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        Optional<User> user=userRepository.findById(id);
        cloudinaryService.delete(cloudinaryService.publicIdExtractor(user.get().getAvatar()));
        userRepository.deleteById(id);
        return "User with id "+id+" deleted";
    }

}
