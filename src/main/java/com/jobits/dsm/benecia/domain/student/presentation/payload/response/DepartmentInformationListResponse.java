package com.jobits.dsm.benecia.domain.student.presentation.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DepartmentInformationListResponse {
    
    private final String image;

    private final String name;

    private final Float percent;

    private final Integer studentCount;

    private final Integer foundJobStudentCount;

    private final String teacherName;

    private final String description;
}
