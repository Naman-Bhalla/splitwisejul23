package com.scaler.splitwisejul23.repositories;

import com.scaler.splitwisejul23.models.User;
import com.scaler.splitwisejul23.models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {

    @Override
    Optional<UserExpense> findById(Long aLong);

    Optional<List<UserExpense>> findAllByUser(User user);

    UserExpense save(UserExpense userExpense);
}
