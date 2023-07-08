package com.scaler.splitwisejul23.repositories;

import com.scaler.splitwisejul23.models.Expense;
import com.scaler.splitwisejul23.models.Group;
import com.scaler.splitwisejul23.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Override
    Optional<Expense> findById(Long aLong);

    Expense save(Expense expense);
}
