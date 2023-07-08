package com.scaler.splitwisejul23.controllers;

import com.scaler.splitwisejul23.dtos.RegisterUserRequestDto;
import com.scaler.splitwisejul23.dtos.RegisterUserResponseDto;
import com.scaler.splitwisejul23.dtos.UpdateProfileRequestDto;
import com.scaler.splitwisejul23.dtos.UpdateProfileResponseDto;
import com.scaler.splitwisejul23.exceptions.UserAlreadyExistsException;
import com.scaler.splitwisejul23.exceptions.UserNotFoundException;
import com.scaler.splitwisejul23.models.User;
import com.scaler.splitwisejul23.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public RegisterUserResponseDto registerUser(RegisterUserRequestDto request) {
        User user;
        RegisterUserResponseDto response = new RegisterUserResponseDto();

        try {
            user = userService.registerUser(request.getUserName(), request.getPhoneNumber(), request.getPassword());
            response.setUserId(user.getId());
            response.setStatus(ResponseStatus.SUCCESS);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(userAlreadyExistsException.getMessage());
        }
        return response;
    }

    public UpdateProfileResponseDto updateProfile(UpdateProfileRequestDto request) {
        User user;
        UpdateProfileResponseDto response = new UpdateProfileResponseDto();
        try {
            user = userService.updateProfile(request.getUserId(), request.getPassword());
            response.setUserId(user.getId());
            response.setStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException userNotFoundException) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(userNotFoundException.getMessage());
        }
        return response;
    }
}
