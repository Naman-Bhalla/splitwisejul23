package com.scaler.splitwisejul23.controllers;

import com.scaler.splitwisejul23.dtos.CreateGroupRequestDto;
import com.scaler.splitwisejul23.dtos.CreateGroupResponseDto;
import com.scaler.splitwisejul23.exceptions.UserDoesNotExistException;
import com.scaler.splitwisejul23.models.Group;
import com.scaler.splitwisejul23.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {
    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    public CreateGroupResponseDto createGroup(CreateGroupRequestDto request) {
        Group group;
        CreateGroupResponseDto response = new CreateGroupResponseDto();

        try {
            group = groupService.createGroup(
                    request.getUserId(),
                    request.getGroupName()
            );
            response.setGroupId(group.getId());
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("Group created successfully");
        } catch (UserDoesNotExistException userDoesNotExistException) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(userDoesNotExistException.getMessage());
        }

        return response;
    }
}
