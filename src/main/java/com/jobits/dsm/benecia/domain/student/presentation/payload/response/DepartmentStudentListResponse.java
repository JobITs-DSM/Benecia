package com.jobits.dsm.benecia.domain.student.presentation.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DepartmentStudentListResponse {

    private final String serialNumber;

    private final String studentNumber;

    private final String name;

    private final Boolean isFoundJob;
}
