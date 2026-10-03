package com.ergiappa.expensetrackerapi.repository;

import com.ergiappa.expensetrackerapi.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
