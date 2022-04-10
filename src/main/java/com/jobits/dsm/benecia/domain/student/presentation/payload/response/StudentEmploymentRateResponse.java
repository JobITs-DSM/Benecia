package com.jobits.dsm.benecia.domain.student.presentation.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentEmploymentRateResponse {

    private final Float percent;
}
