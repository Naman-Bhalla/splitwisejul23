package com.scaler.splitwisejul23.commands;

import com.scaler.splitwisejul23.controllers.ExpenseController;
import com.scaler.splitwisejul23.dtos.ExpenseCreateRequestDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CreateExpenseCommand implements Command {

    private final ExpenseController expenseController;

    public CreateExpenseCommand(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }


    @Override
    public boolean matches(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();

        if (inpWords.size() > 6 && inpWords.get(1).equalsIgnoreCase(CommandKeywords.EXPENSE)) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();

        Long createdBy = Long.valueOf(inpWords.get(0));
        Long groupId = Long.valueOf(inpWords.get(2));
        String payType = inpWords.get(3);
        int amount = Integer.parseInt(inpWords.get(4));
        String expenseRule = inpWords.get(5);
        String description = String.join(" ", inpWords.subList(7,inpWords.size()));

        ExpenseCreateRequestDto expenseCreateRequestDto = new ExpenseCreateRequestDto();
        expenseCreateRequestDto.setGroupId(groupId);
        expenseCreateRequestDto.setCreatedBy(createdBy);
        expenseCreateRequestDto.setExpenseRule(expenseRule);
        expenseCreateRequestDto.setAmount(amount);
        expenseCreateRequestDto.setDescription(description);
        expenseCreateRequestDto.setPayType(payType);

        expenseController.createExpense(expenseCreateRequestDto);
    }
}
