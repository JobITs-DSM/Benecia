package com.jobits.dsm.benecia.domain.admin.service;

import com.jobits.dsm.benecia.domain.admin.exception.UnauthorizedAdminException;
import com.jobits.dsm.benecia.domain.admin.presentation.payload.request.AdminLoginRequest;
import com.jobits.dsm.benecia.domain.admin.presentation.payload.response.AdminTokenResponse;
import com.jobits.dsm.benecia.domain.admin.property.AdminProperty;
import com.jobits.dsm.benecia.global.security.dto.Tokens;
import com.jobits.dsm.benecia.global.security.jwt.JwtTokenProvider;
import com.jobits.dsm.benecia.global.security.property.JwtRoleProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminProperty adminProperty;

    private final JwtTokenProvider jwtTokenProvider;

    public AdminTokenResponse signIn(AdminLoginRequest request) {
        if(!request.getId().equals(adminProperty.getId()) || !request.getPassword().equals(adminProperty.getPassword())) {
            throw UnauthorizedAdminException.EXCEPTION;
        }

        Tokens tokens = jwtTokenProvider.getToken(adminProperty.getId(), JwtRoleProperty.ADMIN_ROLE);

        return AdminTokenResponse.builder()
                .accessToken(tokens.getAccessToken())
                .refreshToken(tokens.getRefreshToken())
                .build();
    }
}
