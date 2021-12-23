package com.jobits.dsm.benecia.global.security.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Tokens {
    private String accessToken;
    private String refreshToken;
}
