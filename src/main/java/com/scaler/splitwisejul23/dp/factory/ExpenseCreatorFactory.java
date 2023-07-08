package com.scaler.splitwisejul23.dp.factory;


import com.scaler.splitwisejul23.dp.strategy.ExpenseCreatorStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.scaler.splitwisejul23.util.AppConstants.EQUAL;
import static com.scaler.splitwisejul23.util.AppConstants.EQUAL_EXPENSE_CREATOR_STRATEGY;

@Component
public class ExpenseCreatorFactory {

    private final ExpenseCreatorStrategy equalExpenseCreatorStrategy;

    public ExpenseCreatorFactory(@Qualifier(EQUAL_EXPENSE_CREATOR_STRATEGY)ExpenseCreatorStrategy equalExpenseCreatorStrategy) {
        this.equalExpenseCreatorStrategy = equalExpenseCreatorStrategy;
    }

    public ExpenseCreatorStrategy getExpenseCreator(String expenseType) {

        return switch (expenseType) {
            case EQUAL -> equalExpenseCreatorStrategy;
            default -> throw new IllegalStateException("Unexpected value");
        };
    }
}
