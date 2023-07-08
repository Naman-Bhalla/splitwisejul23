package com.scaler.splitwisejul23.dp.strategy;

import com.scaler.splitwisejul23.exceptions.GroupNotFoundException;
import com.scaler.splitwisejul23.exceptions.UserNotFoundException;
import com.scaler.splitwisejul23.models.*;
import com.scaler.splitwisejul23.repositories.ExpenseRepository;
import com.scaler.splitwisejul23.repositories.GroupRepository;
import com.scaler.splitwisejul23.repositories.UserExpenseRepository;
import com.scaler.splitwisejul23.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import static com.scaler.splitwisejul23.util.AppConstants.EQUAL_EXPENSE_CREATOR_STRATEGY;

@Component(EQUAL_EXPENSE_CREATOR_STRATEGY)
public class EqualExpenseCreatorStrategy implements com.scaler.splitwisejul23.dp.strategy.ExpenseCreatorStrategy {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;
    private final UserExpenseRepository userExpenseRepository;

    public EqualExpenseCreatorStrategy(UserRepository userRepository, GroupRepository groupRepository, ExpenseRepository expenseRepository, UserExpenseRepository userExpenseRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.userExpenseRepository = userExpenseRepository;
    }

    @Override
    public Expense createExpense(Long createdBy, Long groupId, String payType, int amount, String description) throws GroupNotFoundException, UserNotFoundException {

        Optional<Group> groupOptional = groupRepository.findById(groupId);

        if (groupOptional.isEmpty()) {
            throw new GroupNotFoundException();
        }

        Optional<User> createdByOptional = userRepository.findById(createdBy);

        if (createdByOptional.isEmpty()) {
            throw new UserNotFoundException();
        }

        Group group = groupOptional.get();
        User createdByUser = createdByOptional.get();

        Expense expense = saveExpense(amount, description, createdByUser, group);

        int splitAmount = amount/group.getMembers().size();

        saveUserExpense(group, createdByUser, expense, splitAmount);

        return expense;
    }

    private void saveUserExpense(Group group, User createdByUser, Expense expense, int splitAmount) {
        for (User member : group.getMembers()) {
            UserExpense userExpense  = new UserExpense();
            userExpense.setExpense(expense);
            userExpense.setUser(member);
            userExpense.setAmount(splitAmount);
            if (member.equals(createdByUser)) {
                userExpense.setUserExpenseType(UserExpenseType.PAID);
            } else {
                userExpense.setUserExpenseType(UserExpenseType.HAD_TO_PAY);
            }
            userExpenseRepository.save(userExpense);
        }
    }

    private Expense saveExpense(int amount, String description, User createdBy, Group group) {
        Expense expense = new Expense();
        expense.setExpenseType(ExpenseType.EXPENSE);
        expense.setCreatedBy(createdBy);
        expense.setGroups(group);
        expense.setAmount(amount);
        expense.setDescription(description);
        return expenseRepository.save(expense);
    }
}
