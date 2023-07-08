package com.scaler.splitwisejul23.dtos;

import com.scaler.splitwisejul23.models.UserExpenseType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserExpenseResponseDto extends  BaseResponseDto {
    private Long id;
    private int amount;
    private UserExpenseType userExpenseType;
    private ExpenseResponseDto expenseResponseDto;
}
