package com.ergiappa.expensetrackerapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseResponse {

    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private String category;

    public ExpenseResponse(
            Long id,
            String description,
            BigDecimal amount,
            LocalDate date,
            String category) {

        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }
}