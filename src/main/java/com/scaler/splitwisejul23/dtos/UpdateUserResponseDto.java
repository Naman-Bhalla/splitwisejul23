package com.scaler.splitwisejul23.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateUserResponseDto {

    private Long userId;
    private String status;
    private String message;
}
