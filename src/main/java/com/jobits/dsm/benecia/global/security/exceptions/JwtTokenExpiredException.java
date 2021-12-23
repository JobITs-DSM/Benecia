package com.jobits.dsm.benecia.global.security.exceptions;

import com.jobits.dsm.benecia.global.error.exception.GlobalException;

public class JwtTokenExpiredException extends GlobalException {
    public static final GlobalException EXCEPTION = new JwtTokenExpiredException();

    private JwtTokenExpiredException() {
        super(SecurityErrorCode.JWT_EXPIRED);
    }
}
