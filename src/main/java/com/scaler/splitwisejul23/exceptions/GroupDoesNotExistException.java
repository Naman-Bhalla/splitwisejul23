package com.scaler.splitwisejul23.exceptions;

public class GroupDoesNotExistException extends Exception {
    @Override
    public String getMessage() {
        return "Group with the entered group id does not exist";
    }
}
