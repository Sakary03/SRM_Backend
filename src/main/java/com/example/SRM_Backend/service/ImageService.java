package com.example.SRM_Backend.service;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {
    @Autowired
    private final ServletContext servletContext;

    public ImageService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


    public String uploadImage(MultipartFile file, String UPLOAD_DIR) {
        if (file.isEmpty()) {
            return "Please select a file to upload";
        }

        try {
            byte[] bytes = file.getBytes();

            File dir = new File(UPLOAD_DIR);
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

    public FileSystemResource getAvatar(String filename, String FILE_UPLOAD_DIR) {
        Path filePath = Paths.get(FILE_UPLOAD_DIR, filename);
        File file = filePath.toFile();

        if (!file.exists()) {
            return null;
        }

        return new FileSystemResource(file);
    }

    public boolean delete(String filename, String FILE_UPLOAD_DIR ) {
        try {
            Path file=Paths.get(FILE_UPLOAD_DIR+filename);
            return Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
