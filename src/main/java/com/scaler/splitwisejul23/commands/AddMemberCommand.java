package com.scaler.splitwisejul23.commands;

import com.scaler.splitwisejul23.controllers.GroupController;
import com.scaler.splitwisejul23.dtos.RegisterGroupRequestDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AddMemberCommand implements Command {

    private final GroupController groupController;

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

        Long createdBy = Long.valueOf(inpWords.get(0));
        Long groupId = Long.valueOf(inpWords.get(2));
        Long userId = Long.valueOf(inpWords.get(3));

        RegisterGroupRequestDto registerGroupRequestDto = new RegisterGroupRequestDto();
        registerGroupRequestDto.setGroupId(groupId);
        registerGroupRequestDto.setUserId(userId);
        registerGroupRequestDto.setCreatedBy(createdBy);

        groupController.addMember(registerGroupRequestDto);
    }
}
