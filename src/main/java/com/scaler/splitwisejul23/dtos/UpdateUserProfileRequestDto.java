package com.scaler.splitwisejul23.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateUserProfileRequestDto {
    Long username;
    String newPassword;
}
