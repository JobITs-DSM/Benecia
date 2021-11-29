package com.jobits.dsm.benecia.global.error.exception;

import com.jobits.dsm.benecia.global.error.GlobalErrorCode;

public class NotFoundException extends GlobalException {

    public static final GlobalException EXCEPTION = new NotFoundException();

    private NotFoundException() {
        super(GlobalErrorCode.REQUEST_NOT_FOUND);
    }
}
