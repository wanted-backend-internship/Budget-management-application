package com.example.budget.domain.budget;

import com.example.budget.domain.budget.dto.request.BudgetCreateRequest;
import com.example.budget.domain.budget.dto.request.BudgetUpdateRequest;
import com.example.budget.domain.budget.dto.response.BudgetResponse;
import com.example.budget.domain.budget.entity.Budget;
import com.example.budget.domain.budget.entity.BudgetEditor;
import com.example.budget.domain.user.User;
import com.example.budget.global.exception.ApiException;
import com.example.budget.global.exception.ErrorType;
import com.example.budget.global.util.AuthUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final AuthUtil authUtil;

    @Transactional
    public List<BudgetResponse> createBudgets(List<BudgetCreateRequest> budgetCreateRequests, HttpServletRequest httpServletRequest) {
        authUtil.isLoggedInUserSameCurrentUser(httpServletRequest);

        User user = authUtil.getLoginUser();
        Long totalPercentage = budgetCreateRequests.stream()
                .mapToLong(BudgetCreateRequest::getPercentage)
                .sum();

        if (totalPercentage != 100) {
            throw new ApiException(ErrorType.BUDGET_PERCENT_ERROR);
        }

        for (BudgetCreateRequest budgetCreateRequest : budgetCreateRequests) {
            Budget budget = Budget.builder()
                    .user(user)
                    .category(budgetCreateRequest.getCategory())
                    .percentage(budgetCreateRequest.getPercentage())
                    .build();

            budgetRepository.save(budget);
        }
        return generateResponses();
    }

    @Transactional
    public List<BudgetResponse> updateBudgets(BudgetUpdateRequest budgetUpdateRequest, HttpServletRequest httpServletRequest) {
        authUtil.isLoggedInUserSameCurrentUser(httpServletRequest);

        Budget budget = budgetRepository.findById(budgetUpdateRequest.getBudgetId())
                .orElseThrow(() -> new ApiException(ErrorType.BUDGET_NOT_FOUND));

        BudgetEditor.BudgetEditorBuilder budgetEditorBuilder = budget.toUpdate();
        BudgetEditor budgetEditor = budgetEditorBuilder
                .category(budgetUpdateRequest.getCategory())
                .percentage(budgetUpdateRequest.getPercentage())
                .build();

        budget.update(budgetEditor);

        return generateResponses();
    }

    public List<BudgetResponse> generateResponses() {
        User user = authUtil.getLoginUser();
        List<Budget> budgets = user.getBudgets();
        List<BudgetResponse> budgetResponses = new ArrayList<>();
        for (Budget budget: budgets) {
            BudgetResponse budgetResponse = BudgetResponse.builder()
                    .budgetId(budget.getId())
                    .category(budget.getCategory())
                    .percentage(budget.getPercentage())
                    .build();

            budgetResponses.add(budgetResponse);
        }

        Long totalPercentage = budgetResponses.stream()
                .mapToLong(BudgetResponse::getPercentage)
                .sum();

        if (totalPercentage != 100) {
            throw new ApiException(ErrorType.BUDGET_PERCENT_ERROR);
        }

        return budgetResponses;
    }
}
