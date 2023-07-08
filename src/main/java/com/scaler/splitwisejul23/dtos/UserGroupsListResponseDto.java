package com.scaler.splitwisejul23.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserGroupsListResponseDto extends  BaseResponseDto {
    private List<GroupResponseDto> groupResponseDtos;
}
