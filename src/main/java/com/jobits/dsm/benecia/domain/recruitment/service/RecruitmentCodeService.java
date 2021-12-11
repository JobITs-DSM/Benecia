package com.jobits.dsm.benecia.domain.recruitment.service;

import com.jobits.dsm.benecia.domain.recruitment.code.ProgrammingLanguageCode;
import com.jobits.dsm.benecia.global.code.CodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecruitmentCodeService {

    public CodeResponse getProgrammingLanguage() {
        return CodeResponse.builder()
                .codes(Arrays.stream(ProgrammingLanguageCode.values())
                        .map(programmingLanguage ->
                                CodeResponse.of(programmingLanguage.name(), programmingLanguage.getValue()))
                        .collect(Collectors.toList()))
                .build();
    }
}