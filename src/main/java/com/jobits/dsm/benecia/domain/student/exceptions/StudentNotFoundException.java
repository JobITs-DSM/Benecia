package com.jobits.dsm.benecia.domain.student.exceptions;

import com.jobits.dsm.benecia.global.error.exception.GlobalException;

public class StudentNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new StudentNotFoundException();
    private StudentNotFoundException() {
        super(StudentErrorCode.STUDENT_NOT_FOUND);
    }
}
