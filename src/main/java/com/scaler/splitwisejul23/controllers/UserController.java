package com.scaler.splitwisejul23.controllers;

import com.scaler.splitwisejul23.dtos.RegisterUserRequestDto;
import com.scaler.splitwisejul23.dtos.RegisterUserResponseDto;
import com.scaler.splitwisejul23.dtos.UpdateUserResponseDto;
import com.scaler.splitwisejul23.dtos.UserGroupsResponseDto;
import com.scaler.splitwisejul23.exceptions.UserAlreadyExistsException;
import com.scaler.splitwisejul23.exceptions.UserNotFoundException;
import com.scaler.splitwisejul23.models.Group;
import com.scaler.splitwisejul23.models.User;
import com.scaler.splitwisejul23.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

import static com.scaler.splitwisejul23.util.AppConstants.*;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public RegisterUserResponseDto registerUser(RegisterUserRequestDto request) {
        User user;
        RegisterUserResponseDto response = new RegisterUserResponseDto();

        try {
            user = userService.registerUser(
                    request.getUserName(),
                    request.getPhoneNumber(),
                    request.getPassword()
            );
            response.setUserId(user.getId());
            response.setStatus(SUCCESS);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            response.setStatus(FAILURE);
            response.setMessage(userAlreadyExistsException.getMessage());
        }

        return response;
    }

    public UpdateUserResponseDto updateUser(Long userId, String password) {
        UpdateUserResponseDto response = new UpdateUserResponseDto();

        try {
            User user = userService.updateUser(userId, password);
            response.setUserId(user.getId());
            response.setStatus(SUCCESS);
        } catch (UserNotFoundException userNotFoundException) {
            response.setStatus(FAILURE);
            response.setMessage(userNotFoundException.getMessage());
        }
        return response;
    }

    public UserGroupsResponseDto listGroups(Long userId) {

        UserGroupsResponseDto response = new UserGroupsResponseDto();
        try {
            List<Group> groups = userService.listGroups(userId);
            List<String> groupsNames = new ArrayList<>();
            for (Group group : groups) {
                groupsNames.add(group.getName());
            }
            response.setGroups(groupsNames);
            response.setStatus(SUCCESS);
        } catch (UserNotFoundException userNotFoundException) {
            response.setStatus(FAILURE);
            response.setMessage(userNotFoundException.getMessage());
        }
        return response;
    }
}
