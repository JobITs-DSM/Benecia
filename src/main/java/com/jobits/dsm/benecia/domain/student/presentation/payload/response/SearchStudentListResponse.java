package com.jobits.dsm.benecia.domain.student.presentation.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SearchStudentListResponse {

    private final List<StudentInfo> students;

    @Getter
    @Builder
    public static class StudentInfo {
        private final String name;
        private final String number;
    }
}
