package com.example.SRM_Backend.model;

import com.example.SRM_Backend.dto.request.UserRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String name;
    private String username;
    private Date dob;
    private String email;
    private String address;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private Role role=Role.USER;
    @NotBlank(message = "Password không được để trống")
    private String password;
    @ManyToMany
    @JoinTable(
            name = "user_manga",
            joinColumns = @JoinColumn(name = "userID"),
            inverseJoinColumns = @JoinColumn(name = "bookID"))
    private Set<Manga> mangas;

    public User(Long userID, String name, Date dob, String username, String email, String address, String avatar, Role role, String password) {
        this.userID = userID;
        this.name = name;
        this.dob = dob;
        this.username = username;
        this.email = email;
        this.address = address;
        this.avatar = avatar;
        this.role = role;
        this.password = password;
    }

    public User(UserRequest userRequest) {
        this.setName(userRequest.getName());
        this.setUsername(userRequest.getUsername());
        this.setDob(userRequest.getDob());
        this.setEmail(userRequest.getEmail());
        this.setAddress(userRequest.getAddress());
        this.setRole(userRequest.getRole());
        this.setPassword(userRequest.getPassword());
        this.setAvatar(userRequest.getAvatar());
    }
    public User(String email, String password) {

    }

    public User() {

    }

    // Getters and Setters

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Manga> getMangas() {
        return mangas;
    }

    public void setMangas(Set<Manga> mangas) {
        this.mangas = mangas;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public @NotBlank(message = "Password không được để trống") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password không được để trống") String password) {
        this.password = password;
    }
}


