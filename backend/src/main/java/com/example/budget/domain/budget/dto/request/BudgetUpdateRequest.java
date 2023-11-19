package com.example.budget.domain.budget.dto.request;

import lombok.Getter;

@Getter
public class BudgetUpdateRequest {
    private Long budgetId;
    private String category;
    private Long percentage;

}
