package com.scaler.splitwisejul23.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandRegistry {
    private List<Command> commands;

    @Autowired
    public CommandRegistry(RegisterUserCommand registerUserCommand,
                           UpdateProfileCommand updateProfileCommand,
                           UserGroupsListCommand userGroupsListCommand,
                           UserHistoryCommand userHistoryCommand,
                           ExpenseGroupEqualAddCommand expenseGroupEqualAddCommand) {
        commands = new ArrayList<>();
        commands.add(registerUserCommand);
        commands.add(updateProfileCommand);
        commands.add(userGroupsListCommand);
        commands.add(userHistoryCommand);
        commands.add(expenseGroupEqualAddCommand);
    }

    public void execute(String input) {
        for (Command command: commands) {
            if (command.matches(input)) {
                command.execute(input);
                break;
            }
        }
    }
}
