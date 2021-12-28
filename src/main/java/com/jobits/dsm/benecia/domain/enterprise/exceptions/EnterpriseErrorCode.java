package com.jobits.dsm.benecia.domain.enterprise.exceptions;

import com.jobits.dsm.benecia.global.error.ErrorAttribute;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnterpriseErrorCode implements ErrorAttribute {

    NOT_FOUND(404, "Enterprise Not Found");

    private final int status;
    private final String message;
}
