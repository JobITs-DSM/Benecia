package com.jobits.dsm.benecia.domain.student.presentation.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentCurrentStatusResponse {

    private final String userName;

    private final String userProfileImageUrl;

    private final String employmentStatus;

    private final String enterpriseImageUrl;

    private final String field;
}
