package com.example.budget.domain.budget;

import com.example.budget.domain.budget.dto.request.BudgetsSetRequest;
import com.example.budget.domain.budget.dto.request.BudgetTotalSetRequest;
import com.example.budget.domain.budget.dto.response.BudgetResponse;
import com.example.budget.domain.budget.dto.response.BudgetSetResponse;
import com.example.budget.domain.budget.domain.Budget;
import com.example.budget.domain.user.User;
import com.example.budget.domain.user.UserEditor;
import com.example.budget.domain.user.UserRepository;
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
    public BudgetSetResponse setBudgetTotal(BudgetTotalSetRequest budgetTotalSetRequest, HttpServletRequest httpServletRequest) {
        authUtil.isLoggedInUserSameCurrentUser(httpServletRequest);

        User user = authUtil.getLoginUserForLazy();

        UserEditor.UserEditorBuilder userEditorBuilder = user.toUpdate();
        UserEditor userEditor = userEditorBuilder
                .budgetTotal(budgetTotalSetRequest.getBudgetTotal())
                .build();

        user.update(userEditor);

        BudgetSetResponse budgetSetResponse = BudgetSetResponse.builder()
                .budgetTotal(user.getBudgetTotal())
                .build();

        return budgetSetResponse;
    }

    @Transactional
    public void setBudgets(List<BudgetsSetRequest> budgetsSetRequests, HttpServletRequest httpServletRequest) {
        authUtil.isLoggedInUserSameCurrentUser(httpServletRequest);

        User user = authUtil.getLoginUserForLazy();
        Long totalPercentage = budgetsSetRequests.stream()
                .mapToLong(BudgetsSetRequest::getPercentage)
                .sum();

        if (totalPercentage != 100) {
            throw new ApiException(ErrorType.BUDGET_PERCENT_ERROR);
        }

        if (!user.getBudgets().isEmpty()) {
            List<Budget> budgets = user.getBudgets();
            budgetRepository.deleteAll(budgets);
        }

        for (BudgetsSetRequest budgetsSetRequest : budgetsSetRequests) {
            Budget budget = Budget.builder()
                    .user(user)
                    .category(budgetsSetRequest.getCategory())
                    .percentage(budgetsSetRequest.getPercentage())
                    .build();

            budgetRepository.save(budget);
        }
    }

    @Transactional
    public List<BudgetResponse> returnBudgets() {
        User user = authUtil.getLoginUserForLazy();
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

        return budgetResponses;
    }
}
