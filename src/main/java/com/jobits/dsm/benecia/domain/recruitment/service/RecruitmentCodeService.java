package com.jobits.dsm.benecia.domain.recruitment.service;

import com.jobits.dsm.benecia.domain.recruitment.code.*;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response.HiringAreaCodeResponse;
import com.jobits.dsm.benecia.global.code.CodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecruitmentCodeService {

    public CodeResponse getProgrammingLanguageCodes() {
        return CodeResponse.builder()
                .codes(Arrays.stream(ProgrammingLanguageCode.values())
                        .map(programmingLanguage ->
                                CodeResponse.of(programmingLanguage.name(), programmingLanguage.getValue()))
                        .collect(Collectors.toList()))
                .build();
    }

    public CodeResponse getWelfareCodes() {
        return CodeResponse.builder()
                .codes(Arrays.stream(WelfareCode.values())
                        .map(welfare -> CodeResponse.of(welfare.name(), welfare.getValue()))
                        .collect(Collectors.toList()))
                .build();
    }

    public CodeResponse getFullTimePayCodes() {
        return CodeResponse.builder()
                .codes(Arrays.stream(RecruitmentFullTimePayCode.values())
                        .map(fullTimePay -> CodeResponse.of(fullTimePay.name(), fullTimePay.getValue()))
                        .collect(Collectors.toList()))
                .build();
    }

    public CodeResponse getTechnologyCodes() {
        return CodeResponse.builder()
                .codes(Arrays.stream(TechnologyCode.values())
                        .map(technology -> CodeResponse.of(technology.name(), technology.getValue()))
                        .collect(Collectors.toList()))
                .build();
    }

    public CodeResponse getScreeningProcessCodes() {
        return CodeResponse.builder()
                .codes(Arrays.stream(ScreeningProcessCode.values())
                        .map(screeningProcess -> CodeResponse.of(screeningProcess.name(), screeningProcess.getValue()))
                        .collect(Collectors.toList()))
                .build();
    }

    public HiringAreaCodeResponse getHiringAreaCodes() {
        return HiringAreaCodeResponse.builder()
                .codes(Arrays.stream(HiringAreaCode.values())
                        .map(HiringAreaCodeResponse::of)
                        .collect(Collectors.toList()))
                .build();
    }

    public CodeResponse getReportingTimeCodes() {
        return CodeResponse.builder()
                .codes(Arrays.stream(RecruitmentReportingTimeCode.values())
                        .map(reportingTime -> CodeResponse.of(reportingTime.name(), reportingTime.getValue()))
                        .collect(Collectors.toList()))
                .build();
    }
}