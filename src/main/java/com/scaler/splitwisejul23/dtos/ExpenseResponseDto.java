package com.scaler.splitwisejul23.dtos;

import com.scaler.splitwisejul23.models.ExpenseType;
import com.scaler.splitwisejul23.models.Group;
import com.scaler.splitwisejul23.models.User;
import com.scaler.splitwisejul23.models.UserExpenseType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseResponseDto extends  BaseResponseDto {
    private Long id;
    private int amount;
    private String description;
    private Long createdByUserId;
    private Long groupId;
    private ExpenseType expenseType;
}
