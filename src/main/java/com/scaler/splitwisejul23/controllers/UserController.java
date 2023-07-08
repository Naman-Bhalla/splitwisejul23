package com.scaler.splitwisejul23.controllers;

import com.scaler.splitwisejul23.dtos.RegisterUserRequestDto;
import com.scaler.splitwisejul23.dtos.RegisterUserResponseDto;
import com.scaler.splitwisejul23.dtos.UpdateUserProfileRequestDto;
import com.scaler.splitwisejul23.dtos.UpdateUserProfileResponseDto;
import com.scaler.splitwisejul23.exceptions.UserAlreadyExistsException;
import com.scaler.splitwisejul23.exceptions.UserDoesNotExistException;
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
            user = userService.registerUser(
                    request.getUserName(),
                    request.getPhoneNumber(),
                    request.getPassword()
            );


            response.setUserId(user.getId());
            response.setStatus("SUCCESS");
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            response.setStatus("FAILURE");
            response.setMessage(userAlreadyExistsException.getMessage());
        }

        return response;
    }
    public UpdateUserProfileResponseDto updateUserProfile(UpdateUserProfileRequestDto request){
        User user;
        try{
            user = userService.updateUserProfile(request.getUsername(), request.getNewPassword());
            return UpdateUserProfileResponseDto.builder().status("SUCCESS").userId(user.getId()).build();
        }
        catch (UserDoesNotExistException userDoesNotExistException){
            return UpdateUserProfileResponseDto.builder()
                    .status("FAILURE")
                    .message(userDoesNotExistException.getMessage())
                    .build();
        }

    }
}
