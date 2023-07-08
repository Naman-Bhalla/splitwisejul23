package com.scaler.splitwisejul23.commands;

import com.scaler.splitwisejul23.controllers.GroupController;
import com.scaler.splitwisejul23.dtos.RegisterGroupRequestDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RegisterGroupCommand implements Command {

    private final GroupController groupController;

    public RegisterGroupCommand(GroupController groupController) {
        this.groupController = groupController;
    }

    @Override
    public boolean matches(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();

        if (inpWords.size() == 3 && inpWords.get(1).equalsIgnoreCase(CommandKeywords.REGISTER_GROUP)) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();

        Long userId = Long.valueOf(inpWords.get(0));
        String groupName = inpWords.get(2);

        RegisterGroupRequestDto registerGroupRequestDto = new RegisterGroupRequestDto();
        registerGroupRequestDto.setName(groupName);
        registerGroupRequestDto.setCreatedBy(userId);
        groupController.registerGroup(registerGroupRequestDto);
    }
}
