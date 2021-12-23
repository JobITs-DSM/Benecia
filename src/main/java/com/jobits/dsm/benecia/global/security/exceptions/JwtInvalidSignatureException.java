package com.jobits.dsm.benecia.global.security.exceptions;

import com.jobits.dsm.benecia.global.error.exception.GlobalException;

public class JwtInvalidSignatureException extends GlobalException {
    public static final GlobalException EXCEPTION = new JwtInvalidSignatureException();

    private JwtInvalidSignatureException() {
        super(SecurityErrorCode.JWT_SIGNATURE);
    }
}
