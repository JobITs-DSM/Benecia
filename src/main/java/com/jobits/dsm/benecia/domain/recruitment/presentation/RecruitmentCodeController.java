package com.jobits.dsm.benecia.domain.recruitment.presentation;

import com.jobits.dsm.benecia.domain.recruitment.service.RecruitmentCodeService;
import com.jobits.dsm.benecia.global.code.CodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recruitment/codes")
public class RecruitmentCodeController {

    private final RecruitmentCodeService recruitmentCodeService;

    @GetMapping("/programming-language")
    public CodeResponse programmingLanguage() {
        return recruitmentCodeService.getProgrammingLanguageCodes();
    }

    @GetMapping("/welfare")
    public CodeResponse welfare() {
        return recruitmentCodeService.getWelfareCodes();
    }

    @GetMapping("/full-time-pay")
    public CodeResponse fullTimePay() {
        return recruitmentCodeService.getFullTimePayCodes();
    }

    @GetMapping("/technology")
    public CodeResponse technology() {
        return recruitmentCodeService.getTechnologyCodes();
    }

    @GetMapping("/screening-process")
    public CodeResponse screeningProcess() {
        return recruitmentCodeService.getScreeningProcessCodes();
    }

    @GetMapping("/hiring-area")
    public CodeResponse hiringArea() {
        return recruitmentCodeService.getHiringAreaCodes();
    }

    @GetMapping("/reporting-time")
    public CodeResponse reportingTime() {
        return recruitmentCodeService.getReportingTimeCodes();
    }
}
