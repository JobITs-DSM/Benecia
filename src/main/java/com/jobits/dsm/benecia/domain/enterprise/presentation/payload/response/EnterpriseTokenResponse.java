package com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response;

import com.jobits.dsm.benecia.global.security.dto.Tokens;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@Builder
public class EnterpriseTokenResponse {
    private String accessToken;
    private String refreshToken;
}
