package com.example.SRM_Backend.model;

public enum Role {
    USER,
    ADMIN,
    STAFF;

    public String toAuthority() {
        return "ROLE_" + this.name();
    }
}