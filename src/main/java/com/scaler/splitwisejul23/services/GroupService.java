package com.scaler.splitwisejul23.services;

import com.scaler.splitwisejul23.exceptions.UserAlreadyExistsException;
import com.scaler.splitwisejul23.exceptions.UserDoesNotExistException;
import com.scaler.splitwisejul23.models.Group;
import com.scaler.splitwisejul23.models.User;
import com.scaler.splitwisejul23.models.UserStatus;
import com.scaler.splitwisejul23.repositories.GroupRepository;
import com.scaler.splitwisejul23.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    private UserRepository userRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public Group createGroup(String userId, String groupName) throws UserDoesNotExistException {
        if (!Util.isValidId(userId)) {
            throw new UserDoesNotExistException();
        }
        Long id = Long.parseLong(userId);

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Group group = new Group();
            group.setCreatedBy(user);
            group.setName(groupName);
            group.setMembers(List.of(user));
            return groupRepository.save(group);
        } else {
            throw new UserDoesNotExistException();
        }
    }
}
