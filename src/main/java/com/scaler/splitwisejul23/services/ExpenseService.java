package com.scaler.splitwisejul23.services;

import com.scaler.splitwisejul23.exceptions.GroupNotFoundException;
import com.scaler.splitwisejul23.exceptions.UserNotFoundException;
import com.scaler.splitwisejul23.models.*;
import com.scaler.splitwisejul23.repositories.ExpenseRepository;
import com.scaler.splitwisejul23.repositories.GroupRepository;
import com.scaler.splitwisejul23.repositories.UserExpenseRepository;
import com.scaler.splitwisejul23.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private ExpenseRepository expenseRepository;
    private UserExpenseRepository userExpenseRepository;
    private GroupRepository groupRepository;
    private UserRepository userRepository;

    @Autowired
    public ExpenseService(ExpenseRepository  expenseRepository,
                          UserExpenseRepository userExpenseRepository,
                          UserRepository userRepository,
                          GroupRepository groupRepository) {
        this.expenseRepository = expenseRepository;
        this.userExpenseRepository = userExpenseRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public Expense expenseGroupEqualAdd(Long userId, Long groupId, int amount, String desc) throws UserNotFoundException,
            GroupNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException();
        }
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if(groupOptional.isEmpty()) {
            throw new GroupNotFoundException();
        }
        User user = userOptional.get();
        Group group = groupOptional.get();
        List<User> members = group.getMembers();
        int groupSize = members.size();
        int equalShare = amount / groupSize;

        Expense expense = createNewExpense(amount, desc, user, group);
        for (User member: members) {
            UserExpense userExpense = new UserExpense();
            userExpense.setUser(member);
            if (member.getId().equals(userId)) {
                userExpense.setAmount(amount);
                userExpense.setUserExpenseType(UserExpenseType.PAID);
                userExpense.setExpense(expense);
            }else{
                userExpense.setAmount(equalShare);
                userExpense.setUserExpenseType(UserExpenseType.HAD_TO_PAY);
                userExpense.setExpense(expense);
            }
            userExpenseRepository.save(userExpense);
        }
        return expense;
    }

    private Expense createNewExpense(int amount, String desc, User user, Group group) {
        Expense expense = new Expense();
        expense.setAmount(amount);
        expense.setCreatedBy(user);
        expense.setDescription(desc);
        expense.setGroups(group);
        expense.setExpenseType(ExpenseType.EXPENSE);
        expenseRepository.save(expense);
        return expense;
    }

}
