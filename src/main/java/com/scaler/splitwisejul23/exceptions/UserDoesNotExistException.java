package com.scaler.splitwisejul23.exceptions;

public class UserDoesNotExistException extends Exception {
    @Override
    public String getMessage() {
        return "User with the entered user id does not exist";
    }
}
