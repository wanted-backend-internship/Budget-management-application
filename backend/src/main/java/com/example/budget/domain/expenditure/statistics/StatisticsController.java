package com.example.budget.domain.expenditure.statistics;

import com.example.budget.domain.expenditure.statistics.repository.DailyStatisticsRepository;
import com.example.budget.domain.expenditure.statistics.repository.MonthStatisticsRepository;
import com.example.budget.global.exception.ApiException;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/expenditures")
@RequiredArgsConstructor
public class StatisticsController {
    private final MonthStatisticsRepository monthStatisticsRepository;
    private final DailyStatisticsRepository dailyStatisticsRepository;

    @PostMapping(value = "/statistics/month")
    public ResponseEntity<?> getMonthStatistics () {
        try {
            List<Map<String, Object>> results = monthStatisticsRepository.generateResult();
            return ResponseEntity.ok(results);

        } catch (ApiException apiException) {
            return ResponseEntity.status(apiException.getErrorType().getStatus()).body(apiException.getErrorType().getMessage());
        }
    }

    @PostMapping(value = "/statistics/daily")
    public ResponseEntity<?> getDailyStatistics () {
        try {
            Map<String, Object> results = dailyStatisticsRepository.calculateWeeklyComparison();
            return ResponseEntity.ok(results);

        } catch (ApiException apiException) {
            return ResponseEntity.status(apiException.getErrorType().getStatus()).body(apiException.getErrorType().getMessage());
        }
    }
}
