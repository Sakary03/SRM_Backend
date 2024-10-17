package com.example.SRM_Backend.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Could not fount the user by id: "+id);
    }
}
