package com.example.budget.domain.user;

import com.example.budget.global.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final AuthUtil authUtil;

    public UserInfoResponse findCurrentUser() {
        User user = authUtil.getLoginUser();
        UserInfoResponse userInfoResponse = UserInfoResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .budgetTotal(user.getBudgetTotal())
                .build();

        return userInfoResponse;
    }
}
