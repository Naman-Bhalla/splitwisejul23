package com.scaler.splitwisejul23.services;

import com.scaler.splitwisejul23.exceptions.UserAlreadyExistsException;
import com.scaler.splitwisejul23.models.User;
import com.scaler.splitwisejul23.models.UserStatus;
import com.scaler.splitwisejul23.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String userName, String phoneNumber,
                             String password) throws UserAlreadyExistsException {
        Optional<User> userOptional = userRepository.findByPhone(phoneNumber);

        if (userOptional.isPresent()) {
            if (userOptional.get().getUserStatus().equals(UserStatus.ACTIVE)) {
                throw new UserAlreadyExistsException();
            } else {
                User user = userOptional.get();
                user.setUserStatus(UserStatus.ACTIVE);
                user.setName(userName);
                user.setPassword(password);
                return userRepository.save(user);
            }
        }

        Optional<User> userernameOptional = userRepository.findByName(username);
        if(userernameOptional.isPresent()) {
            throw new UserExistException("User is already exist in the system.");
        }

        User user = new User();
        user.setPhone(phoneNumber);
        user.setName(userName);
        user.setPassword(password);
        user.setUserStatus(UserStatus.ACTIVE);

        return userRepository.save(user);
    }

    public User updateProfile(String username, String password) throws UserNotExistException {
        Optional<User> userernameOptional = userRepository.findByName(username);
        if(userernameOptional.isEmpty()) {
            throw new UserNotExistException("User is not exist in the system.");
        }

        User user = userernameOptional.get();
        user.setPassword(password);
        return userRepository.save(user);
    }
}


// ExpenseController
// splitwise.com/api/settleUp -> SettleUpController