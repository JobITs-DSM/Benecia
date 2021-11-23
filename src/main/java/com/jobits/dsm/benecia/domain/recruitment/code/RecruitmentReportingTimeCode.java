package com.jobits.dsm.benecia.domain.recruitment.code;

import com.jobits.dsm.benecia.domain.recruitment.exception.AttributeConvertFailedException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum RecruitmentReportingTimeCode {

    BEFORE_NINE("REPTM1", "9시 이전"),
    BEFORE_TEN("REPTM2", "10시 이전"),
    FREE_WORK("REPTM3", "자율 출근");

    private final String code;
    private final String value;

    private static final Map<String, RecruitmentReportingTimeCode> map =
            Collections.unmodifiableMap(Arrays.stream(RecruitmentReportingTimeCode.values())
                    .collect(Collectors.toMap(RecruitmentReportingTimeCode::getCode, Function.identity())));

    public static RecruitmentReportingTimeCode find(String dbData) {
        return Optional.of(map.get(dbData))
                .orElseThrow(AttributeConvertFailedException::new);
    }

    @Converter
    public static class RecruitmentReportingTimeCodeCountCodeConverter implements AttributeConverter<RecruitmentReportingTimeCode, String> {

        @Override
        public String convertToDatabaseColumn(RecruitmentReportingTimeCode attribute) {
            return attribute.getCode();
        }

        @Override
        public RecruitmentReportingTimeCode convertToEntityAttribute(String dbData) {
            return Optional.of(RecruitmentReportingTimeCode.find(dbData))
                    .orElseThrow(AttributeConvertFailedException::new);
        }
    }
}
