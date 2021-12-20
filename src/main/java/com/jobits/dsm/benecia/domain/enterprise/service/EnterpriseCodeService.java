package com.jobits.dsm.benecia.domain.enterprise.service;

import com.jobits.dsm.benecia.domain.enterprise.code.BusinessAreaCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseEmployeeCountCode;
import com.jobits.dsm.benecia.global.code.CodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EnterpriseCodeService {

    public CodeResponse getEmployeeCountCodes() {
        return CodeResponse.builder()
                .codes(Arrays.stream(EnterpriseEmployeeCountCode.values())
                        .map(employeeCount -> CodeResponse.of(employeeCount.name(), employeeCount.getValue()))
                        .collect(Collectors.toList()))
                .build();
    }

    public CodeResponse getBusinessAreaCodes() {
        return CodeResponse.builder()
                .codes(Arrays.stream(BusinessAreaCode.values())
                        .map(businessArea -> CodeResponse.of(businessArea.name(), businessArea.getValue()))
                        .collect(Collectors.toList()))
                .build();
    }
}
