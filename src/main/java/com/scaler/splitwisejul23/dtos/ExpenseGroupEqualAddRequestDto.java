package com.scaler.splitwisejul23.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseGroupEqualAddRequestDto {
    private Long userId;
    private Long groupId;
    private int amount;
    private String description;
}
