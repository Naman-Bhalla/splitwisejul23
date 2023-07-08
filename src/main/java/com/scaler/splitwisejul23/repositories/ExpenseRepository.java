package com.scaler.splitwisejul23.repositories;

import com.scaler.splitwisejul23.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Override
    Optional<Expense> findById(Long id);

    Expense save(Expense expense);
}
