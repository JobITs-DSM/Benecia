package com.jobits.dsm.benecia.domain.admin.exception;

import com.jobits.dsm.benecia.global.error.ErrorAttribute;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AdminErrorCode implements ErrorAttribute {

    UNAUTHORIZED(401, "Unauthorized Admin");

    private final int status;
    private final String message;
}
