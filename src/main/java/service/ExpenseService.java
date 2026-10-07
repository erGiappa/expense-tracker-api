package com.ergiappa.expensetrackerapi.service;

import com.ergiappa.expensetrackerapi.dto.ExpenseRequest;
import com.ergiappa.expensetrackerapi.dto.ExpenseResponse;
import com.ergiappa.expensetrackerapi.entity.Expense;
import com.ergiappa.expensetrackerapi.exception.ExpenseNotFoundException;
import com.ergiappa.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<ExpenseResponse> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ExpenseResponse createExpense(ExpenseRequest request) {

        Expense expense = new Expense();

        expense.setDescription(request.getDescription());
        expense.setAmount(request.getAmount());
        expense.setDate(request.getDate());
        expense.setCategory(request.getCategory());

        Expense savedExpense = expenseRepository.save(expense);

        return toResponse(savedExpense);
    }

    public ExpenseResponse updateExpense(
            Long id,
            ExpenseRequest request) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ExpenseNotFoundException(
                                "Expense not found with id: " + id
                        )
                );

        expense.setDescription(request.getDescription());
        expense.setAmount(request.getAmount());
        expense.setDate(request.getDate());
        expense.setCategory(request.getCategory());

        Expense updatedExpense = expenseRepository.save(expense);

        return toResponse(updatedExpense);
    }

    public void deleteExpense(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ExpenseNotFoundException(
                                "Expense not found with id: " + id
                        )
                );

        expenseRepository.delete(expense);
    }

    private ExpenseResponse toResponse(Expense expense) {

        return new ExpenseResponse(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getDate(),
                expense.getCategory()
        );
    }
}