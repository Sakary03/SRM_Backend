package com.example.SRM_Backend.exception;

public class MangaNotFoundException extends RuntimeException {
    public MangaNotFoundException(Long bookid) {
        super("Could not fount the manga by id: "+bookid);
    }
}
