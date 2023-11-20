package com.example.budget.domain.expenditure.dto.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenditureUpdateRequest {
    private Long id;
    private String category;
    private Long expenditureAmount;
    private LocalDateTime expenditureAt;
    private String memo;
    private Boolean isReadable;
}
