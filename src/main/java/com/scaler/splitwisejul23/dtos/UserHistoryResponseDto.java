package com.scaler.splitwisejul23.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserHistoryResponseDto extends  BaseResponseDto {
    private List<UserExpenseResponseDto> userExpenseResponseDtos;
}
