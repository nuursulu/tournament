package com.example.mycli.exceptions.accountException;

public class AccountWrongLogin extends RuntimeException{
    public AccountWrongLogin(String input) {
        super("Wrong input: " + input);
    }
}