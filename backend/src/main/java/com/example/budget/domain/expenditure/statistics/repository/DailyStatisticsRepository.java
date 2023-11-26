package com.example.budget.domain.expenditure.statistics.repository;

import com.example.budget.global.util.AuthUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DailyStatisticsRepository {
    @PersistenceContext
    private EntityManager em;
    private final AuthUtil authUtil;

    public Map<String, Object> calculateWeeklyComparison() {
        Long userId = authUtil.getLoginUserIndex();
        LocalDate today = LocalDate.now();
        LocalDate lastWeekSameDay = today.minusWeeks(1);

        String todayQuery = "SELECT SUM(e.expenditureAmount) FROM Expenditure e " +
                "WHERE e.user.id = :userId AND e.expenditureAt BETWEEN :startOfToday AND :endOfToday";

        String lastWeekQuery = "SELECT SUM(e.expenditureAmount) FROM Expenditure e " +
                "WHERE e.user.id = :userId AND e.expenditureAt BETWEEN :startOfLastWeekSameDay AND :endOfLastWeekSameDay";

        Long todayTotal = em.createQuery(todayQuery, Long.class)
                .setParameter("userId", userId)
                .setParameter("startOfToday", today.atStartOfDay())
                .setParameter("endOfToday", today.plusDays(1).atStartOfDay())
                .getSingleResult();

        Long lastWeekTotal = em.createQuery(lastWeekQuery, Long.class)
                .setParameter("userId", userId)
                .setParameter("startOfLastWeekSameDay", lastWeekSameDay.atStartOfDay())
                .setParameter("endOfLastWeekSameDay", lastWeekSameDay.plusDays(1).atStartOfDay())
                .getSingleResult();

        double changePercentage = 0.0;
        if (todayTotal != null && lastWeekTotal != null && (todayTotal != 0 || lastWeekTotal != 0)) {
            changePercentage = ((double) todayTotal - lastWeekTotal) / (todayTotal + lastWeekTotal) * 100;
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("오늘 총합", todayTotal);
        resultMap.put("저번주 총합", lastWeekTotal);
        resultMap.put("통계", changePercentage);
        return resultMap;
    }
}
