package com.example.budget.domain.expenditure;

import com.example.budget.domain.expenditure.domain.Expenditure;
import com.example.budget.domain.expenditure.domain.ExpenditureEditor;
import com.example.budget.domain.expenditure.dto.request.ExpenditureCreateRequest;
import com.example.budget.domain.expenditure.dto.request.ExpenditureUpdateRequest;
import com.example.budget.domain.expenditure.dto.response.ExpenditureResponse;
import com.example.budget.domain.user.User;
import com.example.budget.global.exception.ApiException;
import com.example.budget.global.exception.ErrorType;
import com.example.budget.global.util.AuthUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExpenditureService {
    private final ExpenditureRepository expenditureRepository;
    private final AuthUtil authUtil;

    @Transactional
    public void createExpenditure(List<ExpenditureCreateRequest> expenditureCreateRequests, HttpServletRequest httpServletRequest) {
        authUtil.isLoggedInUserSameCurrentUser(httpServletRequest);

        User user = authUtil.getLoginUser();

        for (ExpenditureCreateRequest expenditureCreateRequest : expenditureCreateRequests) {
            Expenditure expenditure = Expenditure.builder()
                    .user(user)
                    .category(expenditureCreateRequest.getCategory())
                    .expenditureAmount(expenditureCreateRequest.getExpenditureAmount())
                    .expenditureAt(expenditureCreateRequest.getExpenditureAt())
                    .memo(expenditureCreateRequest.getMemo())
                    .isReadable(expenditureCreateRequest.getIsReadable())
                    .build();

            expenditureRepository.save(expenditure);
        }
    }

    @Transactional
    public void updateExpenditure(ExpenditureUpdateRequest expenditureUpdateRequest, HttpServletRequest httpServletRequest) {
        authUtil.isLoggedInUserSameCurrentUser(httpServletRequest);

        Long userId = authUtil.getLoginUserIndex();
        Expenditure expenditure = expenditureRepository.findByUserId(userId)
                .orElseThrow(() -> new ApiException(ErrorType.EXPENDITURE_NOT_FOUND));

        ExpenditureEditor.ExpenditureEditorBuilder expenditureEditorBuilder = expenditure.toUpdate();
        ExpenditureEditor expenditureEditor = expenditureEditorBuilder
                .category(expenditureUpdateRequest.getCategory())
                .expenditureAmount(expenditureUpdateRequest.getExpenditureAmount())
                .expenditureAt(expenditureUpdateRequest.getExpenditureAt())
                .memo(expenditureUpdateRequest.getMemo())
                .isReadable(expenditureUpdateRequest.getIsReadable())
                .build();

        expenditure.update(expenditureEditor);
    }

    @Transactional
    public void deleteExpenditure(Long expenditureId, HttpServletRequest httpServletRequest) {
        authUtil.isLoggedInUserSameCurrentUser(httpServletRequest);

        Expenditure expenditure = expenditureRepository.findById(expenditureId)
                .orElseThrow(() -> new ApiException(ErrorType.EXPENDITURE_NOT_FOUND));

        expenditureRepository.delete(expenditure);
    }

    @Transactional
    public ExpenditureResponse expenditureDetail (Long expenditureId) {
        Expenditure expenditure = expenditureRepository.findById(expenditureId)
                .orElseThrow(() -> new ApiException(ErrorType.EXPENDITURE_NOT_FOUND));

        ExpenditureResponse expenditureResponse = ExpenditureResponse.builder()
                .expenditureId(expenditure.getId())
                .category(expenditure.getCategory())
                .expenditureAmount(expenditure.getExpenditureAmount())
                .expenditureAt(expenditure.getExpenditureAt())
                .memo(expenditure.getMemo())
                .isReadable(expenditure.getIsReadable())
                .build();

        return expenditureResponse;
    }
}
