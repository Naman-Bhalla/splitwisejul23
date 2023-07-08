package com.scaler.splitwisejul23.commands;

import com.scaler.splitwisejul23.controllers.ResponseStatus;
import com.scaler.splitwisejul23.controllers.UserExpenseController;
import com.scaler.splitwisejul23.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserHistoryCommand implements Command {

    //u1 History

    private UserExpenseController userExpenseController;

    @Autowired
    public UserHistoryCommand(UserExpenseController userExpenseController) {
        this.userExpenseController = userExpenseController;
    }

    @Override
    public boolean matches(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();
        if (inpWords.size() == 2 && inpWords.get(1).equalsIgnoreCase(CommandKeywords.HISTORY)) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();
        Long userId = Long.valueOf(inpWords.get(0));
        UserHistoryRequestDto requestDto = new UserHistoryRequestDto();
        requestDto.setUserId(userId);
        UserHistoryResponseDto responseDto = userExpenseController.userExpenses(requestDto);
        if (responseDto.getStatus().equalsIgnoreCase(ResponseStatus.SUCCESS)) {
            String history = responseDto.getUserExpenseResponseDtos().stream()
                                        .map(userExpenseResponseDto -> userExpenseResponseDto.getExpenseResponseDto()
                                                                                             .getDescription())
                                        .collect(Collectors.joining(",\n"));
            System.out.println("User Groups History : " + history);
        } else {
            System.out.println(responseDto.getMessage());
        }
    }
}
