package com.example.budget.domain.expenditure.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenditureResponse {
    private Long expenditureId;
    private String category;
    private Long expenditureAmount;
    private LocalDateTime expenditureAt;
    private String memo;
    private Boolean isReadable;
}
