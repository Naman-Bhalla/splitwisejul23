package com.scaler.splitwisejul23.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserGroupsResponseDto {

    private Long groupId;
    private List<String> groups;
    private String status;
    private String message;
}
