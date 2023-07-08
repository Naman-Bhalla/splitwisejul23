package com.scaler.splitwisejul23.services;

import com.scaler.splitwisejul23.exceptions.UserAlreadyExistsException;
import com.scaler.splitwisejul23.exceptions.UserNotFoundException;
import com.scaler.splitwisejul23.models.Group;
import com.scaler.splitwisejul23.models.User;
import com.scaler.splitwisejul23.models.UserStatus;
import com.scaler.splitwisejul23.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

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

        User user = new User();
        user.setPhone(phoneNumber);
        user.setName(userName);
        user.setPassword(password);
        user.setUserStatus(UserStatus.ACTIVE);
        return userRepository.save(user);
    }

    public User updateUser(Long userId, String password) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setPassword(password);
                return userRepository.save(user);

        } else {
            throw new UserNotFoundException();
        }
    }

    public List<Group> listGroups(Long userId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get().getGroups();

        } else {
            throw new UserNotFoundException();
        }
    }
}
