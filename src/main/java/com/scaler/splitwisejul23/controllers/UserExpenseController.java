package com.scaler.splitwisejul23.controllers;

import com.scaler.splitwisejul23.dtos.ExpenseResponseDto;
import com.scaler.splitwisejul23.dtos.UserExpenseResponseDto;
import com.scaler.splitwisejul23.dtos.UserHistoryRequestDto;
import com.scaler.splitwisejul23.dtos.UserHistoryResponseDto;
import com.scaler.splitwisejul23.exceptions.UserExpenseNotFoundException;
import com.scaler.splitwisejul23.exceptions.UserNotFoundException;
import com.scaler.splitwisejul23.models.Expense;
import com.scaler.splitwisejul23.models.UserExpense;
import com.scaler.splitwisejul23.services.UserExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserExpenseController {

    private UserExpenseService userExpenseService;

    @Autowired
    public UserExpenseController(UserExpenseService userExpenseService) {
        this.userExpenseService = userExpenseService;
    }

    public UserHistoryResponseDto userExpenses(UserHistoryRequestDto request) {
        List<UserExpense> userExpenses;
        UserHistoryResponseDto response = new UserHistoryResponseDto();
        try {
            userExpenses = userExpenseService.userExpenses(request.getUserId());
            List<UserExpenseResponseDto> userExpenseResponseDtos = userExpenses.stream().map(userExpense -> {
                UserExpenseResponseDto userExpenseResponseDto = new UserExpenseResponseDto();
                userExpenseResponseDto.setId(userExpense.getId());
                userExpenseResponseDto.setUserExpenseType(userExpense.getUserExpenseType());
                userExpenseResponseDto.setAmount(userExpense.getAmount());
                ExpenseResponseDto expenseResponseDto = new ExpenseResponseDto();
                Expense expense = userExpense.getExpense();
                expenseResponseDto.setId(expense.getId());
                expenseResponseDto.setExpenseType(expense.getExpenseType());
                expenseResponseDto.setDescription(expense.getDescription());
                expenseResponseDto.setCreatedByUserId(expense.getCreatedBy().getId());
                expenseResponseDto.setGroupId(expense.getGroups().getId());
                expenseResponseDto.setAmount(expense.getAmount());
                userExpenseResponseDto.setExpenseResponseDto(expenseResponseDto);
                return userExpenseResponseDto;
            }).toList();
            response.setUserExpenseResponseDtos(userExpenseResponseDtos);
            response.setStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException | UserExpenseNotFoundException exception) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(exception.getMessage());
        }
        return response;
    }

}
