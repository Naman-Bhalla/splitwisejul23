package com.scaler.splitwisejul23.exceptions;

public class UserNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return this.getClass().getSimpleName().toString();
    }
}
