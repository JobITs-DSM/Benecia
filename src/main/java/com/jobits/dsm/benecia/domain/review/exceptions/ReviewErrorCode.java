package com.jobits.dsm.benecia.domain.review.exceptions;

import com.jobits.dsm.benecia.global.error.ErrorAttribute;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements ErrorAttribute {
    REVIEW_NOT_FOUND(404, "Review Not Found");

    private final int status;
    private final String message;
}
