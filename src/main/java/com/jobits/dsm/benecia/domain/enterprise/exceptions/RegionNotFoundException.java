package com.jobits.dsm.benecia.domain.enterprise.exceptions;

import com.jobits.dsm.benecia.global.error.exception.GlobalException;

public class RegionNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new RegionNotFoundException();

    private RegionNotFoundException() {
        super(EnterpriseErrorCode.REGION_NOT_FOUND);
    }
}
