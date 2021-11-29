package com.jobits.dsm.benecia.global.error.exception;

import com.jobits.dsm.benecia.global.error.GlobalErrorCode;

public class AttributeConvertFailedException extends GlobalException {

    public static final GlobalException EXCEPTION = new AttributeConvertFailedException();

    private AttributeConvertFailedException() {
        super(GlobalErrorCode.CONVERT_FAILED);
    }
}
