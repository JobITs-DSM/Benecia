package com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response;

import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseDivisionCode;
import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class RecruitmentInfoListForTeacherResponse {
    private final List<RecruitmentInfo> recruitments;

    @Getter
    @Builder
    public static class RecruitmentInfo {
        private final String registrationNumber;
        private final String receptionYear;
        private final RecruitmentStatusCode status;
        private final String name;
        private final List<HiringAreaCode> hiring;
        private final Integer recruitCount;
        private final Long applicantCount;
        private final EnterpriseDivisionCode division;
        private final LocalDate recruitBeginDate;
        private final LocalDate recruitEndDate;
    }
}
