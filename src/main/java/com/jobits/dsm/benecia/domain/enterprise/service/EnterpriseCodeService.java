package com.jobits.dsm.benecia.domain.enterprise.service;

import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseEmployeeCountCode;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.CodeResponse;
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
                        .map(employeeCount -> CodeResponse.of(employeeCount.getCode(), employeeCount.getValue()))
                        .collect(Collectors.toList()))
                .build();
    }
}
