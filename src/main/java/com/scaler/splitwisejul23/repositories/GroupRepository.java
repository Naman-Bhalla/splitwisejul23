package com.scaler.splitwisejul23.repositories;

import com.scaler.splitwisejul23.models.Group;
import com.scaler.splitwisejul23.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Override
    Optional<Group> findById(Long aLong);

    Optional<List<Group>> findByMembers(User user);

    Group save(Group group);
}
