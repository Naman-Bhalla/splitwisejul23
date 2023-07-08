package com.scaler.splitwisejul23.controllers;

import com.scaler.splitwisejul23.dtos.GroupResponseDto;
import com.scaler.splitwisejul23.dtos.UserGroupsListRequestDto;
import com.scaler.splitwisejul23.dtos.UserGroupsListResponseDto;
import com.scaler.splitwisejul23.exceptions.GroupNotFoundException;
import com.scaler.splitwisejul23.exceptions.UserNotFoundException;
import com.scaler.splitwisejul23.models.Group;
import com.scaler.splitwisejul23.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GroupController {

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    public UserGroupsListResponseDto userGroupsList(UserGroupsListRequestDto request) {
        List<Group> groupList;
        UserGroupsListResponseDto response = new UserGroupsListResponseDto();
        try {
            groupList = groupService.userGroupsList(request.getUserId());
            List<GroupResponseDto> responseDtoList = groupList.stream().map(group -> {
                GroupResponseDto groupResponseDto = new GroupResponseDto();
                groupResponseDto.setId(group.getId());
                groupResponseDto.setName(group.getName());
                groupResponseDto.setDescription(group.getDescription());
                groupResponseDto.setCreatedByName(group.getCreatedBy().getName());
                return groupResponseDto;
            }).toList();
            response.setGroupResponseDtos(responseDtoList);
            response.setStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException | GroupNotFoundException exception) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(exception.getMessage());
        }
        return response;
    }

}
