package com.scaler.splitwisejul23.commands;

import com.scaler.splitwisejul23.controllers.ExpenseController;
import com.scaler.splitwisejul23.controllers.GroupController;
import com.scaler.splitwisejul23.controllers.ResponseStatus;
import com.scaler.splitwisejul23.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpenseGroupEqualAddCommand implements Command {

    //u1 Expense g1 iPay 1000 Equal Desc Wifi Bill

    private ExpenseController expenseController;

    @Autowired
    public ExpenseGroupEqualAddCommand(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }

    @Override
    public boolean matches(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();
        if (inpWords.size() > 7 &&
                inpWords.get(1).equalsIgnoreCase(CommandKeywords.EXPENSE) &&
                inpWords.get(3).equalsIgnoreCase(CommandKeywords.IPAY) &&
                inpWords.get(5).equalsIgnoreCase(CommandKeywords.EQUAL) &&
                inpWords.get(6).equalsIgnoreCase(CommandKeywords.DESC)) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();
        Long userId = Long.valueOf(inpWords.get(0));
        Long groupId = Long.valueOf(inpWords.get(2));
        int amount = Integer.parseInt(inpWords.get(4));
        StringBuilder builder = new StringBuilder();
        for(int index = 7; index < inpWords.size(); ++index) {
            builder.append(inpWords.get(index));
        }
        String description = builder.toString();
        ExpenseGroupEqualAddRequestDto requestDto = new ExpenseGroupEqualAddRequestDto();
        requestDto.setUserId(userId);
        requestDto.setGroupId(groupId);
        requestDto.setAmount(amount);
        requestDto.setDescription(description);
        ExpenseAddResponseDto responseDto = expenseController.addGroupEqualExpense(requestDto);
        if (responseDto.getStatus().equalsIgnoreCase(ResponseStatus.SUCCESS)) {
            System.out.println("Expense added with the id : " + responseDto.getExpenseId());
        } else {
            System.out.println(responseDto.getMessage());
        }
    }
}
