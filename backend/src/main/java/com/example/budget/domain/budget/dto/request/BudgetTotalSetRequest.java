package com.example.budget.domain.budget.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BudgetTotalSetRequest {
    private Long budgetTotal;
}
