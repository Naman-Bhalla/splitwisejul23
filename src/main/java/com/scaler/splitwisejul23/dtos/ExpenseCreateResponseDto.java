package com.scaler.splitwisejul23.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExpenseCreateResponseDto {

    private Long expenseId;
    private String status;
    private String message;
}
