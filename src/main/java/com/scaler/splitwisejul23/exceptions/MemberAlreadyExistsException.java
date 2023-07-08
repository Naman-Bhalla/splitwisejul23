package com.scaler.splitwisejul23.exceptions;

public class MemberAlreadyExistsException extends Exception {
    @Override
    public String getMessage() {
        return "User is already a member of this group";
    }
}
