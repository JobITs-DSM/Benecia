package com.jobits.dsm.benecia.domain.student.exceptions;

import com.jobits.dsm.benecia.global.error.exception.GlobalException;

public class DepartmentNotFoundException extends GlobalException {

    public static final GlobalException EXCEPTION = new DepartmentNotFoundException();

    private DepartmentNotFoundException() {
        super(StudentErrorCode.DEPARTMENT_NOT_FOUND);
    }
}
