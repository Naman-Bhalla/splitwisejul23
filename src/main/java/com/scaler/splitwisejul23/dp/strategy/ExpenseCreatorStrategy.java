package com.scaler.splitwisejul23.dp.strategy;

import com.scaler.splitwisejul23.exceptions.GroupNotFoundException;
import com.scaler.splitwisejul23.exceptions.UserNotFoundException;
import com.scaler.splitwisejul23.models.Expense;

public interface ExpenseCreatorStrategy {

    Expense createExpense(Long createdBy, Long groupId, String payType, int amount, String description) throws GroupNotFoundException, UserNotFoundException;
}
