package com.scaler.splitwisejul23.exceptions;

public class UserAlreadyExistsException extends Exception {
    @Override
    public String getMessage() {
        return "User with the entered phone number already exists";
    }
}
