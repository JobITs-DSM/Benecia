package com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LeadingEnterpriseListResponse {

    private final String name;

    private final String region;
}
