package com.jobits.dsm.benecia.global.security.exceptions;

import com.jobits.dsm.benecia.global.error.ErrorAttribute;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SecurityErrorCode implements ErrorAttribute {

    STUDENT_NOT_FOUND(404, "Student Not Found"),
    ENTERPRISE_NOT_FOUND(404, "Enterprise Not Found"),
    JWT_EXPIRED(401, "Jwt Token Expired"),
    JWT_SIGNATURE(401, "Jwt Signature Not Valid");

    private final int status;
    private final String message;
}
