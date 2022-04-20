package com.jobits.dsm.benecia.domain.enterprise.exceptions;

import com.jobits.dsm.benecia.global.error.exception.GlobalException;

public class EnterpriseNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new EnterpriseNotFoundException();

    private EnterpriseNotFoundException() {
        super(EnterpriseErrorCode.ENTERPRISE_NOT_FOUND);
    }
}
