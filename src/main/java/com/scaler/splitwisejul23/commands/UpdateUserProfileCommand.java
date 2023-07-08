package com.scaler.splitwisejul23.commands;

import com.scaler.splitwisejul23.controllers.UserController;
import com.scaler.splitwisejul23.dtos.UpdateUserProfileRequestDto;
import com.scaler.splitwisejul23.dtos.UpdateUserProfileResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

@Component
public class UpdateUserProfileCommand implements Command{
    final UserController userController;

    @Autowired
    public UpdateUserProfileCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean matches(String input) {
        List<String> commandWords = List.of(input.split(" "));
        try {
            Long.parseLong(commandWords.get(0));
        } catch (NumberFormatException nfe) {
            return false;
        }
        return commandWords.size() == 3 && commandWords.get(1).equals(CommandKeywords.UPDATE_PROFILE);
    }

    @Override
    public void execute(String input) {
        List<String> commandWords = List.of(input.split(" "));

        Long id = Long.parseLong(commandWords.get(0));
        String newPassword = commandWords.get(2);

        UpdateUserProfileRequestDto request =  UpdateUserProfileRequestDto.builder()
                .username(id)
                .newPassword(newPassword)
                .build();

        UpdateUserProfileResponseDto response = userController.updateUserProfile(request);
        if(response.getStatus().equals("SUCCESS")) {
            System.out.println(MessageFormat.format("User profile updated with userId - {0}", response.getUserId()));
        }
        else {
            System.out.println(MessageFormat.format("user with userId {0} does not exist", id));
        }
    }
}
