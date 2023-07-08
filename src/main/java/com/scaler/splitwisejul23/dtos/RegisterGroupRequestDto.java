package com.scaler.splitwisejul23.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterGroupRequestDto {

    private Long groupId;
    private String name;
    private String description;
    private Long userId;
    private Long createdBy;

}
