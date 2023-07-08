package com.scaler.splitwisejul23.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequestDto {
    private String userId;
    private String newPassword;
}
