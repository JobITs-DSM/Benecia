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
}
