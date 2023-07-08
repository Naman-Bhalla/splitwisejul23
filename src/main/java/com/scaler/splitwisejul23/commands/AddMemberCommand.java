package com.scaler.splitwisejul23.commands;

import com.scaler.splitwisejul23.controllers.GroupController;
import com.scaler.splitwisejul23.dtos.AddGroupMemberRequestDto;
import com.scaler.splitwisejul23.dtos.AddGroupMemberResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AddMemberCommand implements Command {
    //1 AddMember 1 2
    private GroupController groupController;

    @Autowired
    public AddMemberCommand(GroupController groupController) {
        this.groupController = groupController;
    }

    @Override
    public boolean matches(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();

        if (inpWords.size() == 4 && inpWords.get(1).equalsIgnoreCase(CommandKeywords.ADD_MEMBER)) {
            return true;
        }

        return false;
    }

    @Override
    public void execute(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();

        String userId = inpWords.get(0);
        String groupId = inpWords.get(2);
        String newMemberId = inpWords.get(3);

        AddGroupMemberRequestDto request = new AddGroupMemberRequestDto();
        request.setUserId(userId);
        request.setGroupId(groupId);
        request.setNewMemberId(newMemberId);

        AddGroupMemberResponseDto response = groupController.addGroupMember(request);
        response.printResponse();
    }
}