package com.example.budget.domain.auth;

import com.example.budget.domain.auth.dto.LoginRequest;
import com.example.budget.domain.auth.dto.SignupRequest;
import com.example.budget.domain.user.User;
import com.example.budget.domain.user.UserRepository;
import com.example.budget.global.exception.ApiException;
import com.example.budget.global.exception.ErrorType;
import com.example.budget.global.util.CookieUtil;
import com.example.budget.global.util.RedisUtil;
import com.example.budget.global.util.TokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtil tokenUtil;
    private final CookieUtil cookieUtil;
    private final RedisUtil redisUtil;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void signup(SignupRequest signupRequest) {
        userRepository.findByEmail(signupRequest.getEmail())
                .ifPresent(user -> {
                    throw new ApiException(ErrorType.ALREADY_EXIST_EMAIL);
                });

        // null 값 체크
        if (signupRequest.getEmail() == null || signupRequest.getPassword() == null || signupRequest.getUsername() == null) {
            throw new ApiException(ErrorType.NULL_VALUE_EXIST);
        }

        // 비밀번호 검증
        String passwordPattern = "^(?=(.*[0-9]){0,}(.*[A-Z]){0,}(.*[@#$%^&+=]){0,})((?=.*[A-Z])(?=.*[@#$%^&+=])|(?=.*[0-9])(?=.*[@#$%^&+=])|(?=.*[0-9])(?=.*[A-Z])).{10,}$";
        if (signupRequest.getPassword().matches(passwordPattern)) {
            // 회원가입 완료
            User user = User.builder()
                    .username(signupRequest.getUsername())
                    .email(signupRequest.getEmail())
                    .password(passwordEncoder.encode(signupRequest.getPassword()))
                    .build();

            userRepository.save(user);

        } else {
            throw new ApiException(ErrorType.INVALID_PASSWORD);
        }
    }

    @Transactional
    public void login(LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new ApiException(ErrorType.USER_NOT_FOUND));

        String accessToken = tokenUtil.generateAccessToken(String.valueOf(user.getId()));
        httpServletResponse.setHeader("Authorization", "Bearer " + accessToken);

        if (redisUtil.getData(String.valueOf(user.getId())) == null) {
            String refreshToken = tokenUtil.generateRefreshToken(String.valueOf(user.getId()));

            cookieUtil.create(refreshToken, httpServletResponse);
            redisUtil.setDataExpire(String.valueOf(user.getId()), refreshToken, Duration.ofDays(7));

        } else {
            String refreshToken = redisUtil.getData(String.valueOf(user.getId()));
            cookieUtil.create(refreshToken, httpServletResponse);
        }
    }

    @Transactional
    public void logout (HttpServletResponse response) {
        response.setHeader("Authorization", "");
        cookieUtil.delete("", response);
    }
}
