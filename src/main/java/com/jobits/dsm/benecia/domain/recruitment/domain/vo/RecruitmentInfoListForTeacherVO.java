package com.jobits.dsm.benecia.domain.recruitment.domain.vo;

import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseDivisionCode;
import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class RecruitmentInfoListForTeacherVO {
    private final String registrationNumber;
    private final String receptionYear;
    private final RecruitmentStatusCode status;
    private final String name;
    private final List<HiringAreaCode> hiring;
    private final Integer recruitmentCount;
    private final Long applicantCount;
    private final EnterpriseDivisionCode division;
    private final LocalDate recruitBeginDate;
    private final LocalDate recruitEndDate;

    @QueryProjection
    public RecruitmentInfoListForTeacherVO(String registrationNumber, String receptionYear, RecruitmentStatusCode status, String name, List<HiringAreaCode> hiring, Integer recruitmentCount, Long applicantCount, EnterpriseDivisionCode division, LocalDate recruitBeginDate, LocalDate recruitEndDate) {
        this.registrationNumber = registrationNumber;
        this.receptionYear = receptionYear;
        this.status = status;
        this.name = name;
        this.hiring = hiring;
        this.recruitmentCount = recruitmentCount;
        this.applicantCount = applicantCount;
        this.division = division;
        this.recruitBeginDate = recruitBeginDate;
        this.recruitEndDate = recruitEndDate;
    }
}
