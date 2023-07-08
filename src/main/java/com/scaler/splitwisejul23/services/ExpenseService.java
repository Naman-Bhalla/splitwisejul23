package com.scaler.splitwisejul23.services;


import com.scaler.splitwisejul23.dp.factory.ExpenseCreatorFactory;
import com.scaler.splitwisejul23.exceptions.GroupNotFoundException;
import com.scaler.splitwisejul23.exceptions.UserNotFoundException;
import com.scaler.splitwisejul23.models.Expense;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

   private final ExpenseCreatorFactory expenseCreatorFactory;

    public ExpenseService(ExpenseCreatorFactory expenseCreatorFactory) {
        this.expenseCreatorFactory = expenseCreatorFactory;
    }

    public Expense createExpense(String expenseRule, Long createdBy, Long groupId, String payType, int amount, String description) throws GroupNotFoundException, UserNotFoundException {

        return expenseCreatorFactory.getExpenseCreator(expenseRule).createExpense(createdBy, groupId, payType, amount, description);
    }
}
