package com.jobits.dsm.benecia.domain.recruitment.exceptions;

import com.jobits.dsm.benecia.global.error.ErrorAttribute;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RecruitmentErrorCode implements ErrorAttribute {
    HIRING_AREA_NOT_FOUND(404, "Hiring Area Not Found"),
    RECRUITMENT_NOT_FOUND(404, "Recruitment Not Found");

    private final int status;
    private final String message;
}
