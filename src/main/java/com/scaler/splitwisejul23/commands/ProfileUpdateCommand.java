import com.scaler.splitwisejul23.controllers.UserController;
import com.scaler.splitwisejul23.dto.UpdateProfileRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProfileUpdateCommand implements Command{

    private UserController userController;

    @Autowired
    public ProfileUpdateCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean matches(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();

        if(inputWords.size() == 3 && inputWords.get(1).equalsIgnoreCase(CommandKeywords.UPDATE_PROFILE)) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        List<String> inWords = Arrays.stream(input.split(" ")).toList();
        String username = inWords.get(0);
        String password = inWords.get(2);

        UpdateProfileRequestDto requestDto = new UpdateProfileRequestDto();
        requestDto.setPassword(password);
        requestDto.setUsername(username);

        userController.updateProfile(requestDto);
    }
}