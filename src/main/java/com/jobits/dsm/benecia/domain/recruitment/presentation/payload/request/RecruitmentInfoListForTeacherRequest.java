package com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecruitmentInfoListForTeacherRequest {
    private String recruitmentYear;
    private String keyword;
    private String recruitStatus;
    private String beginDate;
    private String endDate;
}
