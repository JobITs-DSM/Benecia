package com.jobits.dsm.benecia.domain.recruitment.domain.vo;

import com.jobits.dsm.benecia.domain.recruitment.code.*;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RecruitmentDetailVO {
    private final Integer region;
    private final String registrationNumber;
    private final String receptionYear;
    private final String enterpriseBackgroundImageUrl;
    private final String enterpriseProfileImageUrl;
    private final String introduce;
    private final String enterpriseName;
    private final String workPlace;
    private final HiringAreaCode hiringCode;
    private final String task;
    private final Integer recruitCount;
    private final Integer workingHour;
    private final RecruitmentReportingTimeCode reportingTime;
    private final Integer trainingPay;
    private final RecruitmentFullTimePayCode fullTimePay;
    private final LocalDate recruitBeginDate;
    private final LocalDate recruitEndDate;
    private final String otherLanguage;
    private final String otherTechnology;
    private final String preferential;
    private final Integer report;
    private final String qualification;
    private final String otherSpecifics;
    private final String documentation1;
    private final String documentation2;
    private final String documentation3;
    private final Integer form1;
    private final Integer form2;
    private final Integer form3;

    @QueryProjection
    public RecruitmentDetailVO(Integer region, String registrationNumber, String receptionYear,String enterpriseBackgroundImageUrl, String enterpriseProfileImageUrl, String introduce, String enterpriseName, String workPlace, HiringAreaCode hiringCode, String task, Integer recruitCount, Integer workingHour, RecruitmentReportingTimeCode reportingTime, Integer trainingPay, RecruitmentFullTimePayCode fullTimePay, LocalDate recruitBeginDate, LocalDate recruitEndDate, String otherLanguage, String otherTechnology, String preferential, Integer report, String qualification, String otherSpecifics, String documentation1, String documentation2, String documentation3, Integer form1, Integer form2, Integer form3) {
        this.region = region;
        this.registrationNumber = registrationNumber;
        this.receptionYear = receptionYear;
        this.enterpriseBackgroundImageUrl = enterpriseBackgroundImageUrl;
        this.enterpriseProfileImageUrl = enterpriseProfileImageUrl;
        this.introduce = introduce;
        this.enterpriseName = enterpriseName;
        this.workPlace = workPlace;
        this.hiringCode = hiringCode;
        this.task = task;
        this.recruitCount = recruitCount;
        this.workingHour = workingHour;
        this.reportingTime = reportingTime;
        this.trainingPay = trainingPay;
        this.fullTimePay = fullTimePay;
        this.recruitBeginDate = recruitBeginDate;
        this.recruitEndDate = recruitEndDate;
        this.otherLanguage = otherLanguage;
        this.otherTechnology = otherTechnology;
        this.preferential = preferential;
        this.report = report;
        this.qualification = qualification;
        this.otherSpecifics = otherSpecifics;
        this.documentation1 = documentation1;
        this.documentation2 = documentation2;
        this.documentation3 = documentation3;
        this.form1 = form1;
        this.form2 = form2;
        this.form3 = form3;
    }
}
