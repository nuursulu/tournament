package com.example.mycli.exceptions.accountException;

public class AccountConflict extends RuntimeException{
    public AccountConflict(String input) {
        super("Account with login " + input + " is already registered");
    }
}