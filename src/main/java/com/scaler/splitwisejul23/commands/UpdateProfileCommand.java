package com.scaler.splitwisejul23.commands;

import com.scaler.splitwisejul23.controllers.ResponseStatus;
import com.scaler.splitwisejul23.controllers.UserController;
import com.scaler.splitwisejul23.dtos.UpdateProfileRequestDto;
import com.scaler.splitwisejul23.dtos.UpdateProfileResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UpdateProfileCommand implements Command {

    private UserController userController;

    @Autowired
    public UpdateProfileCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean matches(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();
        if (inpWords.size() == 3 && inpWords.get(1).equalsIgnoreCase(CommandKeywords.UPDATE_PROFILE)) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        List<String> inpWords = Arrays.stream(input.split(" ")).toList();
        Long userId = Long.valueOf(inpWords.get(0));
        String password = inpWords.get(2);
        UpdateProfileRequestDto updateProfileRequestDto = new UpdateProfileRequestDto();
        updateProfileRequestDto.setUserId(userId);
        updateProfileRequestDto.setPassword(password);
        UpdateProfileResponseDto responseDto = userController.updateProfile(updateProfileRequestDto);
        if (responseDto.getStatus().equalsIgnoreCase(ResponseStatus.SUCCESS)) {
            System.out.println("Password has been updated for the user: " + responseDto.getUserId());
        } else {
            System.out.println(responseDto.getMessage());
        }
    }
}
