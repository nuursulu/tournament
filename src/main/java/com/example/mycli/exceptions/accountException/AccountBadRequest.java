package com.example.mycli.exceptions.accountException;

public class AccountBadRequest extends RuntimeException{
    public AccountBadRequest(String input) {
        super("Wrong input: " + input);
    }
}