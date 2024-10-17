package com.example.SRM_Backend.controller;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@CrossOrigin("http://localhost:3000")
public class FileUpLoadController {
    private ServletContext servletContext;

    @Autowired
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    // Define the local directory where images will be saved
    private static final String UPLOAD_DIR = "src/main/webapp/resources/images/";

    @PostMapping("/upload/image")
    public String uploadImage(@RequestParam("testUpload") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload";
        }
        try {
            byte[] bytes = file.getBytes();
            String rootPath = this.servletContext.getRealPath("/resources/images");

            File dir = new File(UPLOAD_DIR);
            if (!dir.exists())
                dir.mkdirs();
            String filename = System.currentTimeMillis() + "-" + file.getOriginalFilename();

            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator +
                    +System.currentTimeMillis() + "-" + file.getOriginalFilename());

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return "File uploaded successfully: " + filename;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Error uploading file: " + e.getMessage();
        }
    }
}

