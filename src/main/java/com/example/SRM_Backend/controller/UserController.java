package com.example.SRM_Backend.controller;
import com.example.SRM_Backend.exception.UserNotFoundException;
import com.example.SRM_Backend.model.User;
import com.example.SRM_Backend.repository.UserRepository;
import com.example.SRM_Backend.service.FileService;
import com.example.SRM_Backend.service.UploadUserAvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UploadUserAvatarService uploadUserAvatarService;

    @PostMapping("/user")
    User newUser(@RequestParam("name") String name,
                 @RequestParam("username") String username,
                 @RequestParam("email") String email,
                 @RequestParam("dob") Date dob,
                 @RequestParam("address") String address,
                 @RequestParam("avatar") MultipartFile avatar) {

    String avatarName= uploadUserAvatarService.uploadUserAvatar(avatar);
    User newUser=new User();
    newUser.setName(name);
    newUser.setUsername(username);
    newUser.setEmail(email);
    newUser.setDob(dob);
    newUser.setAddress(address);
    newUser.setAvatar(avatarName);

        return userRepository.save(newUser);
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
            FileService file=new FileService();
            file.delete(user.getAvatar());
            String avatarName= uploadUserAvatarService.uploadUserAvatar(avatar);
            user.setAvatar(avatarName);
            return userRepository.save(user);
        }).orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        Optional<User> user=userRepository.findById(id);
        FileService file=new FileService();
        file.delete(user.get().getAvatar());
        userRepository.deleteById(id);

        return "User with id "+id+" deleted";
    }

}
