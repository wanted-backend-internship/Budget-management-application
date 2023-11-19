package com.example.budget.domain.budget.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudgetResponse {
    private Long budgetId;
    private String category;
    private Long percentage;
}
