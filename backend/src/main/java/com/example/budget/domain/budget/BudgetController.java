package com.example.budget.domain.budget;

import com.example.budget.domain.budget.dto.request.BudgetCreateRequest;
import com.example.budget.domain.budget.dto.request.BudgetUpdateRequest;
import com.example.budget.domain.budget.dto.response.BudgetResponse;
import com.example.budget.global.exception.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/budgets")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @PostMapping(value = "")
    public ResponseEntity<?> createBudgets (List<BudgetCreateRequest> budgetCreateRequests, HttpServletRequest httpServletRequest) {
        try {
            List<BudgetResponse> budgetResponses = budgetService.createBudgets(budgetCreateRequests, httpServletRequest);
            return ResponseEntity.ok(budgetResponses);

        } catch (ApiException apiException) {
            return ResponseEntity.status(apiException.getErrorType().getStatus()).body(apiException.getErrorType().getMessage());
        }
    }

    @PatchMapping(value = "")
    public ResponseEntity<?> updateBudgets (BudgetUpdateRequest budgetUpdateRequest, HttpServletRequest httpServletRequest) {
        try {
            List<BudgetResponse> budgetResponses = budgetService.updateBudgets(budgetUpdateRequest, httpServletRequest);
            return ResponseEntity.ok(budgetResponses);

        } catch (ApiException apiException) {
            return ResponseEntity.status(apiException.getErrorType().getStatus()).body(apiException.getErrorType().getMessage());
        }
    }
}
