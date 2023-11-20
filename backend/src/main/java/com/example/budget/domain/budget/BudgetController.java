package com.example.budget.domain.budget;

import com.example.budget.domain.budget.dto.request.BudgetsSetRequest;
import com.example.budget.domain.budget.dto.request.BudgetTotalSetRequest;
import com.example.budget.domain.budget.dto.response.BudgetResponse;
import com.example.budget.domain.budget.dto.response.BudgetSetResponse;
import com.example.budget.global.exception.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/budgets")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @PatchMapping(value = "/me")
    public ResponseEntity<?> setBudgetTotal (@RequestBody BudgetTotalSetRequest budgetTotalSetRequest, HttpServletRequest httpServletRequest) {
        try {
            BudgetSetResponse budgetSetResponse = budgetService.setBudgetTotal(budgetTotalSetRequest, httpServletRequest);
            return ResponseEntity.ok(budgetSetResponse);

        } catch (ApiException apiException) {
            return ResponseEntity.status(apiException.getErrorType().getStatus()).body(apiException.getErrorType().getMessage());
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> setBudgets (@RequestBody List<BudgetsSetRequest> budgetsSetRequests, HttpServletRequest httpServletRequest) {
        try {
            budgetService.setBudgets(budgetsSetRequests, httpServletRequest);
            return ResponseEntity.ok("예산 등록 성공");

        } catch (ApiException apiException) {
            return ResponseEntity.status(apiException.getErrorType().getStatus()).body(apiException.getErrorType().getMessage());
        }
    }

    @PostMapping(value = "/me")
    public ResponseEntity<?> updateBudgets () {
        try {
            List<BudgetResponse> budgetResponses = budgetService.returnBudgets();
            return ResponseEntity.ok(budgetResponses);

        } catch (ApiException apiException) {
            return ResponseEntity.status(apiException.getErrorType().getStatus()).body(apiException.getErrorType().getMessage());
        }
    }
}
