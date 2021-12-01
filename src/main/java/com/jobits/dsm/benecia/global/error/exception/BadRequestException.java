package com.jobits.dsm.benecia.global.error.exception;

import com.jobits.dsm.benecia.global.error.GlobalErrorCode;

public class BadRequestException extends GlobalException {

    public static final GlobalException EXCEPTION = new BadRequestException();

    private BadRequestException() {
        super(GlobalErrorCode.BAD_REQUEST);
    }
}
