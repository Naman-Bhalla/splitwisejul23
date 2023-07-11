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
// u1 UpdateProfile robinchwan
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

        String password = inpWords.get(2);
        String userName = inpWords.get(0);

        UpdateProfileRequestDto request = new UpdateProfileRequestDto();
        request.setPassword(password);
        request.setUserName(userName);


        userController.updateProfile(request);
        // call user controller an get our action done
    }
}

// Break till 10:15 PM