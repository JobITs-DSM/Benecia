package com.jobits.dsm.benecia.domain.recruitment.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RecruitmentStatusCode {
    RECRUITMENT_REQUEST("STAT01", "접수요청"),
    RECRUITING("STAT02", "모집중"),
    RECRUITED("STAT03", "모집완료");

    private final String code;
    private final String value;
}
