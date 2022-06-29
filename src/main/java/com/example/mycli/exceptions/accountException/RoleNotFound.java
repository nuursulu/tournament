package com.example.mycli.exceptions.accountException;

import org.webjars.NotFoundException;

public class RoleNotFound extends NotFoundException {
    public RoleNotFound(String role) {
        super("Could not find role " + role);
    }
}
