package com.jobits.dsm.benecia.domain.admin.presentation.payload.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AdminTokenResponse {

    private final String accessToken;

    private final String refreshToken;
}
