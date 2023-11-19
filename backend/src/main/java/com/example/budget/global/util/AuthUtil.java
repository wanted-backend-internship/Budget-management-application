package com.example.budget.global.util;

import com.example.budget.domain.user.User;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUtil {
    private final TokenUtil tokenUtil;

    public Long getLoginUserIndex() {
        User user = getLoginUser();
        Long userId = user.getId();
        return userId;
    }

    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return (User) principal;
    }

    public Boolean isLoggedInUserSameCurrentUser (HttpServletRequest request) {
        Long isLoggedInUserId = getLoginUserIndex();
        String accessToken = tokenUtil.getJWTTokenFromHeader(request);
        Long currentUserId = Long.valueOf(tokenUtil.getUserIdFromToken(accessToken));
        if (isLoggedInUserId.equals(currentUserId)) {
            return true;
        }
        return false;
    }
}
