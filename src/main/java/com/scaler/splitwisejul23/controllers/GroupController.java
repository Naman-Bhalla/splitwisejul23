package com.scaler.splitwisejul23.controllers;

import com.scaler.splitwisejul23.dtos.RegisterGroupRequestDto;
import com.scaler.splitwisejul23.dtos.RegisterGroupResponseDto;
import com.scaler.splitwisejul23.exceptions.GroupAlreadyExistException;
import com.scaler.splitwisejul23.exceptions.GroupNotFoundException;
import com.scaler.splitwisejul23.exceptions.UserNotFoundException;
import com.scaler.splitwisejul23.models.Group;
import com.scaler.splitwisejul23.services.GroupService;
import org.springframework.stereotype.Controller;

import static com.scaler.splitwisejul23.util.AppConstants.FAILURE;
import static com.scaler.splitwisejul23.util.AppConstants.SUCCESS;

@Controller
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    public RegisterGroupResponseDto registerGroup(RegisterGroupRequestDto request) {
        RegisterGroupResponseDto response = new RegisterGroupResponseDto();

        try {
            Group group = groupService.registerGroup(request.getName());

            response.setGroupId(group.getId());
            response.setStatus(SUCCESS);
        } catch (GroupAlreadyExistException groupAlreadyExistException) {
            response.setStatus(FAILURE);
            response.setMessage(groupAlreadyExistException.getMessage());
        }

        return response;
    }

    public RegisterGroupResponseDto addMember(RegisterGroupRequestDto request) {

        RegisterGroupResponseDto response = new RegisterGroupResponseDto();

        try {
            Group group = groupService.addMember(request.getGroupId(),request.getUserId());

            response.setGroupId(group.getId());
            response.setGroupId(group.getId());
            response.setStatus(SUCCESS);
        } catch (GroupNotFoundException groupNotFoundException) {
            response.setStatus(FAILURE);
            response.setMessage(groupNotFoundException.getMessage());
        } catch (UserNotFoundException userNotFoundException) {
            response.setStatus(FAILURE);
            response.setMessage(userNotFoundException.getMessage());
        }

        return response;
    }
}
