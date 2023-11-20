package com.example.budget.domain.expenditure;

import com.example.budget.domain.expenditure.domain.Expenditure;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {
    Optional<Expenditure> findByUserId(Long userId);
}