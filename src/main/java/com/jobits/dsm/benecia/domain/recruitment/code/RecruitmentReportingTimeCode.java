package com.jobits.dsm.benecia.domain.recruitment.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecruitmentReportingTimeCode {

    BEFORE_NINE("REPTM1", "9시 이전"),
    BEFORE_TEN("REPTM2", "10시 이전"),
    FREE_WORK("REPTM3", "자율 출근");

    private final String code;
    private final String value;
}
