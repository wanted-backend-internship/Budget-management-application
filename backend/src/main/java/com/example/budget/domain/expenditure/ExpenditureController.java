package com.example.budget.domain.expenditure;

import com.example.budget.domain.expenditure.dto.request.ExpenditureCreateRequest;
import com.example.budget.domain.expenditure.dto.request.ExpenditureUpdateRequest;
import com.example.budget.domain.expenditure.dto.response.ExpenditureResponse;
import com.example.budget.global.exception.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/expenditures")
@RequiredArgsConstructor
public class ExpenditureController {
    private final ExpenditureService expenditureService;

    @PostMapping(value = "")
    public ResponseEntity<?> createExpenditure (@RequestBody List<ExpenditureCreateRequest> expenditureCreateRequests, HttpServletRequest httpServletRequest) {
        try {
            expenditureService.createExpenditure(expenditureCreateRequests, httpServletRequest);
            return ResponseEntity.ok("지출 등록 성공");

        } catch (ApiException apiException) {
            return ResponseEntity.status(apiException.getErrorType().getStatus()).body(apiException.getErrorType().getMessage());
        }
    }

    @PatchMapping(value = "")
    public ResponseEntity<?> updateExpenditure (@RequestBody ExpenditureUpdateRequest expenditureUpdateRequest, HttpServletRequest httpServletRequest) {
        try {
            expenditureService.updateExpenditure(expenditureUpdateRequest, httpServletRequest);
            return ResponseEntity.ok("지출 수정 성공");

        } catch (ApiException apiException) {
            return ResponseEntity.status(apiException.getErrorType().getStatus()).body(apiException.getErrorType().getMessage());
        }
    }

    @DeleteMapping(value = "/{expenditureId}")
    public ResponseEntity<?> deleteExpenditure (@PathVariable Long expenditureId, HttpServletRequest httpServletRequest) {
        try {
            expenditureService.deleteExpenditure(expenditureId, httpServletRequest);
            return ResponseEntity.ok("지출 삭제 성공");

        } catch (ApiException apiException) {
            return ResponseEntity.status(apiException.getErrorType().getStatus()).body(apiException.getErrorType().getMessage());
        }
    }

    @GetMapping(value = "/{expenditureId}")
    public ResponseEntity<?> expenditureDetail (@PathVariable Long expenditureId) {
        try {
            ExpenditureResponse expenditureResponse = expenditureService.expenditureDetail(expenditureId);
            return ResponseEntity.ok(expenditureResponse);

        } catch (ApiException apiException) {
            return ResponseEntity.status(apiException.getErrorType().getStatus()).body(apiException.getErrorType().getMessage());
        }
    }

    @PostMapping(value = "/today")
    public ResponseEntity<?> expenditureToday() {
        try {
            List<Object[]> expenditures = expenditureService.todayExpenditures();
            return ResponseEntity.ok(expenditures);

        } catch (ApiException apiException) {
            return ResponseEntity.status(apiException.getErrorType().getStatus()).body(apiException.getErrorType().getMessage());
        }
    }

}
