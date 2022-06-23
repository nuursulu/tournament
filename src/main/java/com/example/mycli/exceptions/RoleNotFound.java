package com.example.mycli.exceptions;

public class RoleNotFound extends RuntimeException{
    public RoleNotFound(String role) {
        super("Could not find role " + role);
    }
}
