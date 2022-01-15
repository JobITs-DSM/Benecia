package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseDivisionCode;
import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class RecruitmentVO {
    private final String receptionYear;
    private final Enterprise registrationNumber;
    private final RecruitmentStatusCode status;
    private final String name;
    private final EnterpriseDivisionCode division;
    private final LocalDate recruitBeginDate;
    private final LocalDate recruitEndDate;

    @QueryProjection
    public RecruitmentVO(String receptionYear, Enterprise registrationNumber, RecruitmentStatusCode status, String name, EnterpriseDivisionCode division, LocalDate recruitBeginDate, LocalDate recruitEndDate) {
        this.receptionYear = receptionYear;
        this.registrationNumber = registrationNumber;
        this.status = status;
        this.name = name;
        this.division = division;
        this.recruitBeginDate = recruitBeginDate;
        this.recruitEndDate = recruitEndDate;
    }
}
