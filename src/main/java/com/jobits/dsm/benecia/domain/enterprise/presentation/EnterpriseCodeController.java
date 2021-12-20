package com.jobits.dsm.benecia.domain.enterprise.presentation;

import com.jobits.dsm.benecia.global.code.CodeResponse;
import com.jobits.dsm.benecia.domain.enterprise.service.EnterpriseCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/enterprise/codes")
public class EnterpriseCodeController {

    private final EnterpriseCodeService enterpriseCodeService;

    @GetMapping("/employee-count")
    public CodeResponse employeeCount() {
        return enterpriseCodeService.getEmployeeCountCodes();
    }

    @GetMapping("/business-area")
    public CodeResponse businessArea() {
        return enterpriseCodeService.getBusinessArea();
    }
}
