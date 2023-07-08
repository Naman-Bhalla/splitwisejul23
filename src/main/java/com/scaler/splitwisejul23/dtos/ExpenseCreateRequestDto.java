package com.scaler.splitwisejul23.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExpenseCreateRequestDto {

    private String expenseRule;
    private Long createdBy;
    private int amount;
    private Long groupId;
    private String description;
    private String payType;
}
