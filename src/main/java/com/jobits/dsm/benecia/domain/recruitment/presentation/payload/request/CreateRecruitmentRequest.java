package com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request;

import com.jobits.dsm.benecia.domain.recruitment.code.*;
import com.jobits.dsm.benecia.domain.recruitment.domain.Documentation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecruitmentRequest {

    @NotBlank
    private String receptionYear;

    private List<HiringArea> hiring;

    private List<String> tags;

    private List<ScreeningProcessCode> screeningProcesses;

    private List<TechnologyCode> technologies;

    private List<WelfareCode> welfare;

    private List<ProgrammingLanguageCode> programmingLanguages;

    @NotBlank
    private String workPlace;

    private Integer workingHour;

    private RecruitmentReportingTimeCode reportingTime;

    private Integer trainingPay;

    private RecruitmentFullTimePayCode fullTimePay;

    private Integer recruitCount;

    private LocalDate recruitBeginDate;

    private LocalDate recruitEndDate;

    @NotBlank
    private String otherLanguage;

    @NotBlank
    private String otherTechnology;

    @NotBlank
    private String preferential;

    private Integer report;

    @NotBlank
    private String qualification;

    @NotBlank
    private String otherSpecifics;

    private Documentation documentation;

    private Integer form1;

    private Integer form2;

    private Integer form3;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HiringArea {

        private HiringAreaCode code;

        private String task;
    }
}
