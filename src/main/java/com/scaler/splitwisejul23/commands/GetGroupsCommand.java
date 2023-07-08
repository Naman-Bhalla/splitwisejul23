package com.scaler.splitwisejul23.commands;

import com.scaler.splitwisejul23.controllers.UserController;
import com.scaler.splitwisejul23.dtos.GetGroupsRequestDto;
import com.scaler.splitwisejul23.dtos.GetGroupsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class GetGroupsCommand implements Command {
    //1 Groups
    private UserController userController;

    @Autowired
    public GetGroupsCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean matches(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();

        if (inpWords.size() == 2 && inpWords.get(1).equalsIgnoreCase(CommandKeywords.GET_GROUPS)) {
            return true;
        }

        return false;
    }

    @Override
    public void execute(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();

        String userId = inpWords.get(0);

        GetGroupsRequestDto request = new GetGroupsRequestDto();
        request.setUserId(userId);

        GetGroupsResponseDto response = userController.getGroupsByUser(request);
        response.printResponse();
    }
}