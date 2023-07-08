package com.scaler.splitwisejul23.controllers;

import com.scaler.splitwisejul23.dtos.ExpenseCreateRequestDto;
import com.scaler.splitwisejul23.dtos.ExpenseCreateResponseDto;
import com.scaler.splitwisejul23.exceptions.GroupNotFoundException;
import com.scaler.splitwisejul23.exceptions.UserNotFoundException;
import com.scaler.splitwisejul23.models.Expense;
import com.scaler.splitwisejul23.services.ExpenseService;
import org.springframework.stereotype.Controller;

import static com.scaler.splitwisejul23.util.AppConstants.FAILURE;
import static com.scaler.splitwisejul23.util.AppConstants.SUCCESS;

@Controller
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public ExpenseCreateResponseDto createExpense(ExpenseCreateRequestDto request) {

        ExpenseCreateResponseDto response = new ExpenseCreateResponseDto();

        try {
            Expense expense = expenseService.createExpense(
                    request.getExpenseRule()
                    , request.getCreatedBy()
                    , request.getGroupId()
                    , request.getPayType()
                    , request.getAmount()
                    , request.getDescription()
            );

            response.setExpenseId(expense.getId());
            response.setStatus(SUCCESS);
        } catch (GroupNotFoundException groupNotFoundException) {
            response.setStatus(FAILURE);
            response.setMessage(groupNotFoundException.getMessage());
        } catch (UserNotFoundException userNotFoundException) {
            response.setStatus(FAILURE);
            response.setMessage(userNotFoundException.getMessage());
        }

        return response;
    }
}
