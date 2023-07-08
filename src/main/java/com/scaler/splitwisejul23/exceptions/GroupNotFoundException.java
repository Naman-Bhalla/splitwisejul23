package com.scaler.splitwisejul23.exceptions;

public class GroupNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return this.getClass().getSimpleName().toString();
    }
}
