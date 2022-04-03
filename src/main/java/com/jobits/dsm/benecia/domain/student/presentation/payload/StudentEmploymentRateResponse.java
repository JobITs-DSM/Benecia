package com.jobits.dsm.benecia.domain.student.presentation.payload;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentEmploymentRateResponse {

    private final Float percent;
}
