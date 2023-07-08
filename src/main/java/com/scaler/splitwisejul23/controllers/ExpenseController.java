package com.scaler.splitwisejul23.controllers;

import com.scaler.splitwisejul23.dtos.*;
import com.scaler.splitwisejul23.exceptions.GroupNotFoundException;
import com.scaler.splitwisejul23.exceptions.UserNotFoundException;
import com.scaler.splitwisejul23.models.Expense;
import com.scaler.splitwisejul23.models.Group;
import com.scaler.splitwisejul23.services.ExpenseService;
import com.scaler.splitwisejul23.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ExpenseController {

    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public ExpenseAddResponseDto addGroupEqualExpense(ExpenseGroupEqualAddRequestDto request) {
        Expense expense;
        ExpenseAddResponseDto response = new ExpenseAddResponseDto();
        try {
            expense = expenseService.expenseGroupEqualAdd(request.getUserId(), request.getGroupId(),
                                                                   request.getAmount(), request.getDescription());
            response.setExpenseId(expense.getId());
            response.setStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException | GroupNotFoundException exception) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(exception.getMessage());
        }
        return response;
    }

}
