package com.jobits.dsm.benecia.global.error.exception;

import com.jobits.dsm.benecia.global.error.GlobalErrorCode;

public class UnexpectedException extends GlobalException {

    public static final GlobalException EXCEPTION = new UnexpectedException();

    private UnexpectedException() {
        super(GlobalErrorCode.UNEXPECTED_ERROR);
    }
}
