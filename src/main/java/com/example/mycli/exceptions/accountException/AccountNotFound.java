package com.example.mycli.exceptions.accountException;

import org.webjars.NotFoundException;

public class AccountNotFound extends NotFoundException {
    public AccountNotFound(String id) {
        super("Could not find account " + id);
    }
}
