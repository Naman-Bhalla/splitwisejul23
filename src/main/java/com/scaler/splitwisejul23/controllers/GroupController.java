package com.scaler.splitwisejul23.controllers;

import com.scaler.splitwisejul23.dtos.AddGroupMemberRequestDto;
import com.scaler.splitwisejul23.dtos.AddGroupMemberResponseDto;
import com.scaler.splitwisejul23.dtos.CreateGroupRequestDto;
import com.scaler.splitwisejul23.dtos.CreateGroupResponseDto;
import com.scaler.splitwisejul23.exceptions.GroupDoesNotExistException;
import com.scaler.splitwisejul23.exceptions.InvalidGroupMemberException;
import com.scaler.splitwisejul23.exceptions.MemberAlreadyExistsException;
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

    public AddGroupMemberResponseDto addGroupMember(AddGroupMemberRequestDto request) {
        Group group;
        AddGroupMemberResponseDto response = new AddGroupMemberResponseDto();

        try {
            group = groupService.addMember(
                    request.getUserId(),
                    request.getGroupId(),
                    request.getNewMemberId()
            );
            response.setGroupId(group.getId());
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("New member added successfully to a group");
        } catch (UserDoesNotExistException userDoesNotExistException) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(userDoesNotExistException.getMessage());
        } catch (GroupDoesNotExistException e) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(e.getMessage());
        } catch (MemberAlreadyExistsException e) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(e.getMessage());
        } catch (InvalidGroupMemberException e) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(e.getMessage());
        }

        return response;
    }
}
