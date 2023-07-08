package com.scaler.splitwisejul23.services;

import com.scaler.splitwisejul23.exceptions.GroupAlreadyExistException;
import com.scaler.splitwisejul23.exceptions.GroupNotFoundException;
import com.scaler.splitwisejul23.exceptions.UserNotFoundException;
import com.scaler.splitwisejul23.models.Group;
import com.scaler.splitwisejul23.models.User;
import com.scaler.splitwisejul23.repositories.GroupRepository;
import com.scaler.splitwisejul23.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public Group registerGroup(String name) throws GroupAlreadyExistException {

        Optional<Group> groupOptional = groupRepository.findByName(name);

        if (groupOptional.isPresent()) {
            throw new GroupAlreadyExistException();
        }

        Group group = new Group();
        group.setName(name);
        return groupRepository.save(group);
    }

    public Group addMember(Long groupId, Long userId) throws GroupNotFoundException, UserNotFoundException {

        Optional<Group> groupOptional = groupRepository.findById(groupId);

        if (groupOptional.isEmpty()) {
            throw new GroupNotFoundException();
        }

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException();
        }

        Group group = groupOptional.get();
        List<User> members = group.getMembers();
        members.add(userOptional.get());
        group.setMembers(members);
        return groupRepository.save(group);
    }
}
