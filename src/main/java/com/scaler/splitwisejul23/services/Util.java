package com.scaler.splitwisejul23.services;

public class Util {
    public static boolean isValidId(String id) {
        try {
            Long.parseLong(id);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
