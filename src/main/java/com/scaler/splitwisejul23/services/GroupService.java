package com.scaler.splitwisejul23.services;

import com.scaler.splitwisejul23.exceptions.*;
import com.scaler.splitwisejul23.models.Group;
import com.scaler.splitwisejul23.models.User;
import com.scaler.splitwisejul23.repositories.GroupRepository;
import com.scaler.splitwisejul23.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Group addMember(String userId, String groupId, String newUserId) throws UserDoesNotExistException, GroupDoesNotExistException, MemberAlreadyExistsException, InvalidGroupMemberException {
        if (!Util.isValidId(userId) || !Util.isValidId(newUserId)) {
            throw new UserDoesNotExistException();
        }
        if (!Util.isValidId(groupId)) {
            throw new GroupDoesNotExistException();
        }

        Long userID = Long.parseLong(userId);
        Long newMemberId = Long.parseLong(newUserId);
        Long groupID = Long.parseLong(groupId);

        Optional<User> userOptional = userRepository.findById(userID);
        if (userOptional.isEmpty()) {
            throw new UserDoesNotExistException();
        }

        Optional<User> newMemberOptional = userRepository.findById(newMemberId);
        if (newMemberOptional.isEmpty()) {
            throw new UserDoesNotExistException();
        }

        Optional<Group> groupOptional = groupRepository.findById(groupID);
        if (groupOptional.isEmpty()) {
            throw new GroupDoesNotExistException();
        }

        Group group = groupOptional.get();
        User user = userOptional.get();
        User newMember = newMemberOptional.get();

        boolean hasRight = false;
        for (User tempUser: group.getMembers()) {
            if (user.getId() == tempUser.getId()) {
                hasRight = true;
            }
            if (newMember.getId() == tempUser.getId()) {
                throw new MemberAlreadyExistsException();
            }
        }
        if (!hasRight) {
            throw new InvalidGroupMemberException();
        }

        group.getMembers().add(newMember);
        groupRepository.save(group);

        return group;
    }
}
