package com.ergiappa.expensetrackerapi.service;

import com.ergiappa.expensetrackerapi.entity.Expense;
import com.ergiappa.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense expense) {

        Optional<Expense> existingExpense = expenseRepository.findById(id);

        if (existingExpense.isEmpty()) {
            throw new RuntimeException("Expense not found");
        }

        Expense expenseToUpdate = existingExpense.get();

        expenseToUpdate.setDescription(expense.getDescription());
        expenseToUpdate.setAmount(expense.getAmount());
        expenseToUpdate.setDate(expense.getDate());
        expenseToUpdate.setCategory(expense.getCategory());

        return expenseRepository.save(expenseToUpdate);
    }
}
