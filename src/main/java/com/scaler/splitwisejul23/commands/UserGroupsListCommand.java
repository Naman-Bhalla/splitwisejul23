package com.scaler.splitwisejul23.commands;

import com.scaler.splitwisejul23.controllers.GroupController;
import com.scaler.splitwisejul23.controllers.ResponseStatus;
import com.scaler.splitwisejul23.dtos.GroupResponseDto;
import com.scaler.splitwisejul23.dtos.UserGroupsListRequestDto;
import com.scaler.splitwisejul23.dtos.UserGroupsListResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserGroupsListCommand implements Command {

    //u1 Groups

    private GroupController groupController;

    @Autowired
    public UserGroupsListCommand(GroupController groupController) {
        this.groupController = groupController;
    }

    @Override
    public boolean matches(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();
        if (inpWords.size() == 2 && inpWords.get(1).equalsIgnoreCase(CommandKeywords.GROUPS)) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();
        Long userId = Long.valueOf(inpWords.get(0));
        UserGroupsListRequestDto requestDto = new UserGroupsListRequestDto();
        requestDto.setUserId(userId);
        UserGroupsListResponseDto responseDto = groupController.userGroupsList(requestDto);
        if (responseDto.getStatus().equalsIgnoreCase(ResponseStatus.SUCCESS)) {
            String groupNames = responseDto.getGroupResponseDtos().stream()
                                        .map(GroupResponseDto::getName)
                                        .collect(Collectors.joining(","));
            System.out.println("User Groups are : " + groupNames);
        } else {
            System.out.println(responseDto.getMessage());
        }
    }
}
