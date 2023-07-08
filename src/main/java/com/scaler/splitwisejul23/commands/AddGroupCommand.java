package com.scaler.splitwisejul23.commands;

import com.scaler.splitwisejul23.controllers.GroupController;
import com.scaler.splitwisejul23.controllers.UserController;
import com.scaler.splitwisejul23.dtos.CreateGroupRequestDto;
import com.scaler.splitwisejul23.dtos.CreateGroupResponseDto;
import com.scaler.splitwisejul23.dtos.UpdateProfileRequestDto;
import com.scaler.splitwisejul23.dtos.UpdateProfileResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AddGroupCommand implements Command {
    //1 AddGroup roommates
    private GroupController groupController;

    @Autowired
    public AddGroupCommand(GroupController groupController) {
        this.groupController = groupController;
    }

    @Override
    public boolean matches(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();

        if (inpWords.size() == 3 && inpWords.get(1).equalsIgnoreCase(CommandKeywords.ADD_GROUP)) {
            return true;
        }

        return false;
    }

    @Override
    public void execute(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();

        String userId = inpWords.get(0);
        String groupName = inpWords.get(2);

        CreateGroupRequestDto request = new CreateGroupRequestDto();
        request.setUserId(userId);
        request.setGroupName(groupName);

        CreateGroupResponseDto response = groupController.createGroup(request);
        response.printResponse();
    }
}