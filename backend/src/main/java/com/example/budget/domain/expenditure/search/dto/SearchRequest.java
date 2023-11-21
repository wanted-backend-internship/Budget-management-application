package com.example.budget.domain.expenditure.search.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class SearchRequest {
    private LocalDate startDate;
    private LocalDate endDate;
}
