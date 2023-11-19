package com.example.budget.domain.user;

import com.example.budget.global.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserInfoController {
    private final UserInfoService userInfoService;

    @PostMapping(value = "/me")
    public ResponseEntity<?> findCurrentUser() {
        try {
            UserInfoResponse userInfoResponse = userInfoService.findCurrentUser();
            return ResponseEntity.ok(userInfoResponse);

        } catch (ApiException apiException) {
            return ResponseEntity.status(apiException.getErrorType().getStatus()).body(apiException.getErrorType().getMessage());
        }
    }
}
