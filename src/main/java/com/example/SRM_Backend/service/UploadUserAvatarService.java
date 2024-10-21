package com.example.SRM_Backend.service;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UploadUserAvatarService {

    private static final String USER_AVATAR_UPLOAD_DIR = "src/main/webapp/resources/images/UserAvatar/";
    private final ServletContext servletContext;

    @Autowired
    public UploadUserAvatarService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String uploadUserAvatar(MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload";
        }

        try {
            byte[] bytes = file.getBytes();

            File dir = new File(USER_AVATAR_UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filename = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            File serverFile = new File(dir, filename);

            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                stream.write(bytes);
            }

            return filename;

        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading file: " + e.getMessage();
        }
    }
}
