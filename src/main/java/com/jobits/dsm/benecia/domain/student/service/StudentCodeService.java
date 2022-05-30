package com.jobits.dsm.benecia.domain.student.service;

import com.jobits.dsm.benecia.domain.student.code.DepartmentCode;
import com.jobits.dsm.benecia.global.code.CodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentCodeService {

    public CodeResponse getDepartmentCodes() {
        return CodeResponse.builder()
                .codes(Arrays.stream(DepartmentCode.values())
                        .map(department -> CodeResponse.of(department.name(), department.getValue()))
                        .collect(Collectors.toList()))
                .build();
    }
}

