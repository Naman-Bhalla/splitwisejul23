package com.scaler.splitwisejul23.controllers;

import com.scaler.splitwisejul23.dtos.RegisterUserRequestDto;
import com.scaler.splitwisejul23.dtos.RegisterUserResponseDto;
import com.scaler.splitwisejul23.exceptions.UserAlreadyExistsException;
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

    public UpdateProfileResponseDto updateProfile(UpdateProfileRequestDto requestDto) {
        UpdateProfileResponseDto responseDto = new UpdateProfileResponseDto();
        try {
            User user = userService.updateProfile(requestDto.getUsername(), requestDto.getPassword());
            responseDto.setId(user.getId());
            responseDto.setStatus("Success");
            responseDto.setMessage("Password Updated.");

        } catch (UserNotExistException e) {
            responseDto.setMessage("NOT FOUND");
            responseDto.setMessage("User not exist, Please register");
        } catch (Exception e) {
            responseDto.setMessage("FAILED");
        }
        return responseDto;
    }
}
