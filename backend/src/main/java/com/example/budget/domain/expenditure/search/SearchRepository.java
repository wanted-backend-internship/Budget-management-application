package com.example.budget.domain.expenditure.search;

import com.example.budget.domain.expenditure.domain.Expenditure;
import com.example.budget.domain.expenditure.search.dto.SearchCategoryRequest;
import com.example.budget.domain.expenditure.search.dto.SearchMoneyRangeRequest;
import com.example.budget.domain.expenditure.search.dto.SearchRequest;
import jakarta.persistence.EntityManager;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SearchRepository {
    private final EntityManager entityManager;

    public List<Object[]> findExpenditureByDate(SearchRequest request) {
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();

        String jpqlAggregation = "SELECT " +
                "FUNCTION('DATE', e.expenditureAt) AS expenditureAt, " +
                "e.category, " +
                "SUM(e.expenditureAmount) AS totalOfCategory " +
                "FROM Expenditure e " +
                "WHERE e.expenditureAt BETWEEN :startDateTime AND :endDateTime " +
                "GROUP BY FUNCTION('DATE', e.expenditureAt), e.category";

        List<Object[]> aggregatedResults = entityManager.createQuery(jpqlAggregation)
                .setParameter("startDateTime", startDate.atStartOfDay())
                .setParameter("endDateTime", endDate.atTime(LocalTime.MAX))
                .getResultList();

        Map<Date, Map<String, Object[]>> dateMap = new LinkedHashMap<>();
        for (Object[] aggregatedResult : aggregatedResults) {
            Date date = (Date) aggregatedResult[0];
            String category = (String) aggregatedResult[1];
            Long totalOfCategory = (Long) aggregatedResult[2];

            dateMap.computeIfAbsent(date, k -> new LinkedHashMap<>())
                    .put(category, new Object[]{category, totalOfCategory, new ArrayList<Expenditure>()});
        }

        for (Map.Entry<Date, Map<String, Object[]>> dateEntry : dateMap.entrySet()) {
            Date date = dateEntry.getKey();
            Map<String, Object[]> categoryMap = dateEntry.getValue();

            for (Map.Entry<String, Object[]> categoryEntry : categoryMap.entrySet()) {
                String category = categoryEntry.getKey();
                List<Expenditure> expenditures = fetchExpenditures(entityManager, date, category, startDate, endDate);
                ((List<Expenditure>) categoryEntry.getValue()[2]).addAll(expenditures);
            }
        }

        return dateMap.entrySet().stream()
                .map(entry -> new Object[]{entry.getKey(), entry.getValue()})
                .collect(Collectors.toList());
    }

    private List<Expenditure> fetchExpenditures(EntityManager entityManager, Date date, String category, LocalDate startDate, LocalDate endDate) {
        String jpqlEntities = "SELECT e FROM Expenditure e " +
                "WHERE FUNCTION('DATE', e.expenditureAt) = :date AND e.category = :category " +
                "AND e.expenditureAt BETWEEN :startDateTime AND :endDateTime";

        return entityManager.createQuery(jpqlEntities, Expenditure.class)
                .setParameter("date", date)
                .setParameter("category", category)
                .setParameter("startDateTime", startDate.atStartOfDay())
                .setParameter("endDateTime", endDate.atTime(LocalTime.MAX))
                .getResultList();
    }

    public List<Object[]> findExpenditureByDateAndCategory(SearchCategoryRequest request) {
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        String category = request.getCategory();

        String jpqlAggregation = "SELECT " +
                "FUNCTION('DATE', e.expenditureAt) AS expenditureAt, " +
                "e.category, " +
                "SUM(e.expenditureAmount) AS totalOfCategory " +
                "FROM Expenditure e " +
                "WHERE e.expenditureAt BETWEEN :startDateTime AND :endDateTime " +
                "AND e.category = :category " +
                "GROUP BY FUNCTION('DATE', e.expenditureAt), e.category";

        List<Object[]> aggregatedResults = entityManager.createQuery(jpqlAggregation)
                .setParameter("startDateTime", startDate.atStartOfDay())
                .setParameter("endDateTime", endDate.atTime(LocalTime.MAX))
                .setParameter("category", category)
                .getResultList();

        List<Object[]> finalResults = new ArrayList<>();
        for (Object[] aggregatedResult : aggregatedResults) {
            Date date = (Date) aggregatedResult[0];

            String jpqlDetails = "SELECT e FROM Expenditure e " +
                    "WHERE FUNCTION('DATE', e.expenditureAt) = :date " +
                    "AND e.category = :category";

            List<Expenditure> expenditures = entityManager.createQuery(jpqlDetails, Expenditure.class)
                    .setParameter("date", date)
                    .setParameter("category", category)
                    .getResultList();

            finalResults.add(new Object[]{date, aggregatedResult[1], aggregatedResult[2], expenditures});
        }

        return finalResults;
    }

    public List<Object[]> findExpenditureByDateAndMoneyRange(SearchMoneyRangeRequest request) {
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        Long minMoney = request.getMinMoney();
        Long maxMoney = request.getMaxMoney();

        String jpqlAggregation = "SELECT " +
                "FUNCTION('DATE', e.expenditureAt) AS expenditureAt, " +
                "e.category, " +
                "SUM(e.expenditureAmount) AS totalOfCategory " +
                "FROM Expenditure e " +
                "WHERE e.expenditureAt BETWEEN :startDateTime AND :endDateTime " +
                "AND e.expenditureAmount BETWEEN :minMoney AND :maxMoney " +
                "GROUP BY FUNCTION('DATE', e.expenditureAt), e.category";

        List<Object[]> aggregatedResults = entityManager.createQuery(jpqlAggregation)
                .setParameter("startDateTime", startDate.atStartOfDay())
                .setParameter("endDateTime", endDate.atTime(LocalTime.MAX))
                .setParameter("minMoney", minMoney)
                .setParameter("maxMoney", maxMoney)
                .getResultList();

        List<Object[]> finalResults = new ArrayList<>();
        for (Object[] aggregatedResult : aggregatedResults) {
            Date date = (Date) aggregatedResult[0];
            String category = (String) aggregatedResult[1];

            String jpqlDetails = "SELECT e FROM Expenditure e " +
                    "WHERE FUNCTION('DATE', e.expenditureAt) = :date " +
                    "AND e.category = :category " +
                    "AND e.expenditureAmount BETWEEN :minMoney AND :maxMoney";

            List<Expenditure> expenditures = entityManager.createQuery(jpqlDetails, Expenditure.class)
                    .setParameter("date", date)
                    .setParameter("category", category)
                    .setParameter("minMoney", minMoney)
                    .setParameter("maxMoney", maxMoney)
                    .getResultList();

            finalResults.add(new Object[]{date, category, aggregatedResult[2], expenditures});
        }

        return finalResults;
    }
}
