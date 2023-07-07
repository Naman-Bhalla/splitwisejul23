package com.scaler.splitwisejul23.commands;

import com.scaler.splitwisejul23.controllers.UserController;
import com.scaler.splitwisejul23.dtos.UpdateProfileRequestDto;
import com.scaler.splitwisejul23.dtos.UpdateProfileResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UpdateProfileCommand implements Command {
    //1 UpdateProfile robinchwan
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

        String userId = inpWords.get(0);
        String newPassword = inpWords.get(2);

        UpdateProfileRequestDto request = new UpdateProfileRequestDto();
        request.setUserId(userId);
        request.setNewPassword(newPassword);

        UpdateProfileResponseDto response = userController.updateProfile(request);
        response.printResponse();
    }
}