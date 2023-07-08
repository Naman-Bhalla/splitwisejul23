package com.scaler.splitwisejul23.repositories;

import com.scaler.splitwisejul23.models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserExpenseRepository  extends JpaRepository<UserExpense, Long> {

    @Override
    Optional<UserExpense> findById(Long id);

    UserExpense save(UserExpense userExpense);
}
