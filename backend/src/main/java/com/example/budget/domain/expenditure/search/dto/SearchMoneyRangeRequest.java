package com.example.budget.domain.expenditure.search.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class SearchMoneyRangeRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long minMoney;
    private Long maxMoney;
}
