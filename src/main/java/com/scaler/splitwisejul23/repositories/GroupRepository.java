package com.scaler.splitwisejul23.repositories;

import com.scaler.splitwisejul23.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Override
    Optional<Group> findById(Long aLong);

    Optional<Group> findByName(String name);

    Group save(Group group);
}
