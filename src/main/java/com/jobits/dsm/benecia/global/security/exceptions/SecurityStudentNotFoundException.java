package com.jobits.dsm.benecia.global.security.exceptions;

import com.jobits.dsm.benecia.global.error.exception.GlobalException;

public class SecurityStudentNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new SecurityStudentNotFoundException();

    private SecurityStudentNotFoundException() {
        super(SecurityErrorCode.STUDENT_NOT_FOUND);
    }
}
