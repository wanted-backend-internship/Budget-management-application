package com.example.budget.domain.budget.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudgetCreateRequest {
    private String category;
    private Long percentage;
}
