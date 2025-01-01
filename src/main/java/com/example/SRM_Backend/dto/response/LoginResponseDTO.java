package com.example.SRM_Backend.dto.response;

import com.example.SRM_Backend.model.User;

public class LoginResponseDTO {
    private UserInfoResponse userInfo;
    private String accessToken;
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public UserInfoResponse getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User user) {
        this.userInfo=new UserInfoResponse(user);
    }
}
