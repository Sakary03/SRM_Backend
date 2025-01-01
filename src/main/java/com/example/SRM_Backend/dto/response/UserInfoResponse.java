package com.example.SRM_Backend.dto.response;

import com.example.SRM_Backend.model.User;

public class UserInfoResponse {
    private Long userID;
    private String username;
    private String name;
    private String dob;
    private String email;
    private String avatar;
    private String role;

    public UserInfoResponse(User user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.dob = user.getDob().toString();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
        this.role = user.getRole().toString();
        this.userID = user.getUserID();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserid() {
        return userID;
    }

    public void setUserid(Long userid) {
        this.userID = userid;
    }
}
