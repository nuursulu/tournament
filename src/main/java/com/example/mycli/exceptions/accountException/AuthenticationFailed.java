package com.example.mycli.exceptions.accountException;

public class AuthenticationFailed extends RuntimeException{
    public AuthenticationFailed() {
        super("Authentication failed");    
    }

}
