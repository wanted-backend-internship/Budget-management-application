package com.example.budget.domain.budget;

import com.example.budget.domain.budget.entity.Budget;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findById(Long budgetId);
}