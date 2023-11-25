package com.example.budget.domain.expenditure;

import com.example.budget.domain.expenditure.domain.Expenditure;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {
    Optional<Expenditure> findByUserId(Long userId);

    @Query("SELECT e.category, SUM(e.expenditureAmount) FROM Expenditure e WHERE e.user.id = :userId AND e.expenditureAt BETWEEN :startDate AND :endDate GROUP BY e.category")
    List<Object[]> findUserExpendituresByDateAndCategory(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}