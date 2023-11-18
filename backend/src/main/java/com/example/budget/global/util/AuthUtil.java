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
        Optional<User> user = getLoginUser();
        Long userId = user.get().getId();
        return userId;
    }

    public Optional<User> getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return (Optional<User>) principal;
    }

    public Long  getLoginUserIdFromToken(HttpServletRequest request) {
        String accessToken = tokenUtil.getJWTTokenFromHeader(request);
        Long userId = Long.parseLong(tokenUtil.getUserIdFromToken(accessToken));
        return userId;
    }
}
