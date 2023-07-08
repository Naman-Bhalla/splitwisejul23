package com.scaler.splitwisejul23.exceptions;

public class InvalidGroupMemberException extends Exception {
    @Override
    public String getMessage() {
        return "Entered user is not a participant of this group";
    }
}
