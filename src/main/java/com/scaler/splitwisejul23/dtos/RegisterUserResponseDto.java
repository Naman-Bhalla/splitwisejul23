package com.scaler.splitwisejul23.dtos;

import com.scaler.splitwisejul23.controllers.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserResponseDto extends BaseResponseDto {
    private Long userId;

    @Override
    public void printResponse() {
        super.printResponse();
        if (getStatus() != null && getStatus().equals(ResponseStatus.SUCCESS)) {
            System.out.println("User ID: " + userId);
        }
    }

}
