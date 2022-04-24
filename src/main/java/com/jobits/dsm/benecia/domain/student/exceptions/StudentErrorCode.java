package com.jobits.dsm.benecia.domain.student.exceptions;

import com.jobits.dsm.benecia.global.error.ErrorAttribute;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudentErrorCode implements ErrorAttribute {
    STUDENT_NOT_FOUND(404, "Student Not Found");

    private final int status;
    private final String message;
}
