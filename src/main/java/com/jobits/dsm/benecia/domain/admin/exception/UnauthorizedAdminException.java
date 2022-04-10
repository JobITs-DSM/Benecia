package com.jobits.dsm.benecia.domain.admin.exception;

import com.jobits.dsm.benecia.global.error.exception.GlobalException;

public class UnauthorizedAdminException extends GlobalException {

    public static final GlobalException EXCEPTION = new UnauthorizedAdminException();

    private UnauthorizedAdminException() {
        super(AdminErrorCode.UNAUTHORIZED);
    }
}
