package com.jobits.dsm.benecia.domain.recruitment.exceptions;

import com.jobits.dsm.benecia.global.error.exception.GlobalException;

public class HiringAreaNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new HiringAreaNotFoundException();
    private HiringAreaNotFoundException() {
        super(RecruitmentErrorCode.HIRING_AREA_NOT_FOUND);
    }
}
