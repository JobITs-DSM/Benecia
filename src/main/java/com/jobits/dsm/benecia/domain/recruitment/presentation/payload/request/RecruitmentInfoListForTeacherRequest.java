package com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request;

import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RecruitmentInfoListForTeacherRequest {
    private String recruitmentYear;
    private String keyword;
    private RecruitmentStatusCode recruitStatus;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate beginDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;
}
