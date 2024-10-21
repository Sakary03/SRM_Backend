package com.example.SRM_Backend.service;


import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class GetFileService {

    private static final String USER_AVATAR_UPLOAD_DIR = "src/main/webapp/resources/images/UserAvatar/";

    public File getAvatarFileByName(String filename) throws IOException {
        File avatarFile = new File(USER_AVATAR_UPLOAD_DIR + filename);
        System.out.println(filename);
        if (avatarFile.exists() && avatarFile.isFile()) {
            return avatarFile;
        } else {
            throw new IOException("File not found: " + filename);
        }
    }

    public byte[] getAvatarBytesByName(String filename) throws IOException {
        File avatarFile = new File(USER_AVATAR_UPLOAD_DIR + filename);
        System.out.println(filename);
        if (avatarFile.exists() && avatarFile.isFile()) {
            return Files.readAllBytes(avatarFile.toPath());
        } else {
            throw new IOException("File not found: " + filename);
        }
    }
}
