package com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response;

import com.jobits.dsm.benecia.domain.recruitment.code.ProgrammingLanguageCode;
import com.jobits.dsm.benecia.domain.recruitment.code.ScreeningProcessCode;
import com.jobits.dsm.benecia.domain.recruitment.code.TechnologyCode;
import com.jobits.dsm.benecia.domain.recruitment.code.WelfareCode;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class RecruitmentDetailResponse {
    private final String enterpriseBackgroundImageUrl;
    private final String enterpriseProfileImageUrl;
    private final String introduce;
    private final String enterpriseName;
    private final String workPlace;
    private final HiringInfo hiringArea;
    private final Integer recruitCount;
    private final List<String> tags;
    private final List<ScreeningProcessCode> screeningProcesses;
    private final List<TechnologyCode> technologies;
    private final List<WelfareCode> welfare;
    private final List<ProgrammingLanguageCode> programmingLanguages;
    private final Integer workingHour;
    private final String reportingTime;
    private final Integer trainingPay;
    private final String fullTimePay;
    private final LocalDate recruitBeginDate;
    private final LocalDate recruitEndDate;
    private final String otherLanguage;
    private final String otherTechnology;
    private final String preferential;
    private final Integer report;
    private final String qualification;
    private final String otherSpecifics;
    private final DocumentationType documentationType;
    private final Integer form1;
    private final Integer form2;
    private final Integer form3;

    @Getter
    @Builder
    public static class HiringInfo {
        private final String code;
        private final String task;
    }

    @Getter
    @Builder
    public static class DocumentationType {
        private final String documentation1;
        private final String documentation2;
        private final String documentation3;
    }
}