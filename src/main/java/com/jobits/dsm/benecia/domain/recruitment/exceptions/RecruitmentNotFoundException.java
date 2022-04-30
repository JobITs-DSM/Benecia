package com.jobits.dsm.benecia.domain.recruitment.exceptions;

import com.jobits.dsm.benecia.global.error.exception.GlobalException;

public class RecruitmentNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new RecruitmentNotFoundException();
    private RecruitmentNotFoundException() {
        super(RecruitmentErrorCode.RECRUITMENT_NOT_FOUND);
    }
}
