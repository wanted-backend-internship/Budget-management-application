package com.example.budget.domain.expenditure.search;

import com.example.budget.domain.expenditure.search.dto.SearchCategoryRequest;
import com.example.budget.domain.expenditure.search.dto.SearchMoneyRangeRequest;
import com.example.budget.domain.expenditure.search.dto.SearchRequest;
import com.example.budget.global.exception.ApiException;
import com.example.budget.global.util.AuthUtil;
import com.querydsl.core.Tuple;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/expenditures/search")
@RequiredArgsConstructor
public class SearchController {
    private final AuthUtil authUtil;
    private final SearchRepository searchRepository;

    @PostMapping(value = "")
    public ResponseEntity<?> createExpenditure (@RequestBody SearchRequest searchRequest, HttpServletRequest httpServletRequest) {
        try {
            authUtil.isLoggedInUserSameCurrentUser(httpServletRequest);
            List<Object[]> responses = searchRepository.findExpenditureByDate(searchRequest);
            return ResponseEntity.ok(responses);

        } catch (ApiException apiException) {
            return ResponseEntity.status(apiException.getErrorType().getStatus()).body(apiException.getErrorType().getMessage());
        }
    }

    @PostMapping(value = "/category")
    public ResponseEntity<?> findExpenditureByDateAndCategory (@RequestBody SearchCategoryRequest searchCategoryRequest, HttpServletRequest httpServletRequest) {
        try {
            authUtil.isLoggedInUserSameCurrentUser(httpServletRequest);
            List<Object[]> responses = searchRepository.findExpenditureByDateAndCategory(searchCategoryRequest);
            return ResponseEntity.ok(responses);

        } catch (ApiException apiException) {
            return ResponseEntity.status(apiException.getErrorType().getStatus()).body(apiException.getErrorType().getMessage());
        }
    }

    @PostMapping(value = "/money-range")
    public ResponseEntity<?> createExpenditure (@RequestBody SearchMoneyRangeRequest searchMoneyRangeRequest, HttpServletRequest httpServletRequest) {
        try {
            authUtil.isLoggedInUserSameCurrentUser(httpServletRequest);
            List<Object[]> responses = searchRepository.findExpenditureByDateAndMoneyRange(searchMoneyRangeRequest);
            return ResponseEntity.ok(responses);

        } catch (ApiException apiException) {
            return ResponseEntity.status(apiException.getErrorType().getStatus()).body(apiException.getErrorType().getMessage());
        }
    }
}
