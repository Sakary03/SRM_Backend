package com.example.SRM_Backend.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    private static final String FILE_UPLOAD_DIR = "src/main/webapp/resources/images/UserAvatar/";

    public FileSystemResource getAvatar(String filename) {
        Path filePath = Paths.get(FILE_UPLOAD_DIR, filename);
        File file = filePath.toFile();

        if (!file.exists()) {
            return null; // Hoặc ném một exception nếu bạn muốn
        }

        return new FileSystemResource(file);
    }

    public boolean delete(String filename) {
        try {
            Path file=Paths.get(FILE_UPLOAD_DIR+filename);
            return Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
