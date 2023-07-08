package com.scaler.splitwisejul23.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponseDto {
    private String status;
    private String message;

    public void printResponse() {
        if (status != null) {
            System.out.print(status);
        }
        if (message != null) {
            System.out.print(": ");
            System.out.print(message);
        }
        System.out.println();
    }
}