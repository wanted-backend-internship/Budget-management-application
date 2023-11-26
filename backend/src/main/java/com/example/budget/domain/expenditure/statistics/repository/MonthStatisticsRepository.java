package com.example.budget.domain.expenditure.statistics.repository;

import com.example.budget.global.util.AuthUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MonthStatisticsRepository {
    @PersistenceContext
    private EntityManager em;
    private final AuthUtil authUtil;

    public List<Object[]> calculateThisMonthlyComparison() {
        Long userId = authUtil.getLoginUserIndex();
        LocalDate today = LocalDate.now();
        YearMonth thisMonth = YearMonth.from(today);

        String thisMonthQuery = "SELECT e.category, SUM(e.expenditureAmount) FROM Expenditure e " +
                "WHERE e.user.id = :userId AND YEAR(e.expenditureAt) = :year AND MONTH(e.expenditureAt) = :month " +
                "GROUP BY e.category";

        List<Object[]> thisMonthData = em.createQuery(thisMonthQuery, Object[].class)
                .setParameter("userId", userId)
                .setParameter("year", thisMonth.getYear())
                .setParameter("month", thisMonth.getMonthValue())
                .getResultList();

        return thisMonthData;
    }

    public List<Object[]> calculateLastMonthlyComparison() {
        Long userId = authUtil.getLoginUserIndex();
        YearMonth lastMonth = YearMonth.from(LocalDate.now().minusMonths(1));

        String lastMonthQuery = "SELECT e.category, SUM(e.expenditureAmount) FROM Expenditure e " +
                "WHERE e.user.id = :userId AND YEAR(e.expenditureAt) = :year AND MONTH(e.expenditureAt) = :month " +
                "GROUP BY e.category";

        List<Object[]> lastMonthData = em.createQuery(lastMonthQuery, Object[].class)
                .setParameter("userId", userId)
                .setParameter("year", lastMonth.getYear())
                .setParameter("month", lastMonth.getMonthValue())
                .getResultList();

        return lastMonthData;
    }

    public List<Map<String, Object>> generateResult() {
        List<Object[]> lastMonthData = calculateLastMonthlyComparison();
        List<Object[]> thisMonthData = calculateThisMonthlyComparison();

        Map<String, Long> lastMonthMap = lastMonthData.stream()
                .collect(Collectors.toMap(
                        e -> (String) e[0], // 카테고리
                        e -> (Long) e[1]    // 총액
                ));

        List<Map<String, Object>> comparisonList = thisMonthData.stream()
                .map(e -> {
                    String category = (String) e[0];
                    Long thisMonthTotal = (Long) e[1];
                    Long lastMonthTotal = lastMonthMap.getOrDefault(category, 0L);

                    double changePercentage = 0.0;
                    if (thisMonthTotal != 0 || lastMonthTotal != 0) {
                        changePercentage = ((double)thisMonthTotal - lastMonthTotal) / (thisMonthTotal + lastMonthTotal) * 100;
                    }

                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("카테고리", category);
                    resultMap.put("통계", changePercentage);
                    return resultMap;
                })
                .collect(Collectors.toList());

        return comparisonList;
    }
}