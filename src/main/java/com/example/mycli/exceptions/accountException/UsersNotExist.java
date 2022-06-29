package com.example.mycli.exceptions.accountException;

import org.webjars.NotFoundException;

public class UsersNotExist extends NotFoundException {
    public UsersNotExist() {
        super("Users don't Exist");
    }
}
