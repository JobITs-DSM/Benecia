package com.jobits.dsm.benecia.global.security.exceptions;

import com.jobits.dsm.benecia.global.error.exception.GlobalException;

public class SecurityEnterpriseNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new SecurityEnterpriseNotFoundException();

    private SecurityEnterpriseNotFoundException() {
        super(SecurityErrorCode.STUDENT_NOT_FOUND);
    }
}
