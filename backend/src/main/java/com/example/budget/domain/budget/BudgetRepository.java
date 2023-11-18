package com.example.budget.domain.budget;

import com.example.budget.domain.expenditure.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Expenditure, Long> {
}