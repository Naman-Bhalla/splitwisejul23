package com.scaler.splitwisejul23.services;

import com.scaler.splitwisejul23.exceptions.UserExpenseNotFoundException;
import com.scaler.splitwisejul23.exceptions.UserNotFoundException;
import com.scaler.splitwisejul23.models.ExpenseType;
import com.scaler.splitwisejul23.models.User;
import com.scaler.splitwisejul23.models.UserExpense;
import com.scaler.splitwisejul23.repositories.UserExpenseRepository;
import com.scaler.splitwisejul23.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserExpenseService {

    private UserExpenseRepository userExpenseRepository;
    private UserRepository userRepository;

    @Autowired
    public UserExpenseService(UserExpenseRepository userExpenseRepository, UserRepository userRepository) {
        this.userExpenseRepository = userExpenseRepository;
        this.userRepository = userRepository;
    }

    public List<UserExpense> userExpenses(Long userId) throws UserNotFoundException, UserExpenseNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = userOptional.get();
        Optional<List<UserExpense>> expensesOptional = userExpenseRepository.findByUser(user);
        if (expensesOptional.isEmpty()) {
            throw new UserExpenseNotFoundException();
        }
        return expensesOptional.get();
    }


}
