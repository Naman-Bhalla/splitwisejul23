package com.scaler.splitwisejul23.commands;

import com.scaler.splitwisejul23.controllers.UserController;
import com.scaler.splitwisejul23.dtos.UpdateProfileRequestDto;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProfileCommand implements Command {
  //UpdateProfile 132524545 241946
  //cmd           ph no     newPass
  private final UserController userController;

  @Autowired
  public UpdateProfileCommand(UserController userController) {
    this.userController = userController;
  }


  @Override
  public boolean matches(String input) {
    List<String> inpWords = Arrays.stream(input.split(" ")).toList();

    return inpWords.size() == 3 && inpWords.get(0).equalsIgnoreCase(CommandKeywords.UPDATE_PROFILE);
  }

  @Override
  public void execute(String input) {
    List<String> inpWords = Arrays.stream(input.split(" ")).toList();

    String phoneNumber = inpWords.get(1);
    String pass = inpWords.get(2);

    UpdateProfileRequestDto updateProfileRequestDto = new UpdateProfileRequestDto();
    updateProfileRequestDto.setPhoneNumber(phoneNumber);
    updateProfileRequestDto.setPassword(pass);

    userController.updateProfile(updateProfileRequestDto);
  }
}
