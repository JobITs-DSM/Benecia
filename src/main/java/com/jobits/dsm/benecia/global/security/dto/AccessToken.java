package com.jobits.dsm.benecia.global.security.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccessToken {
    private String accessToken;
}
