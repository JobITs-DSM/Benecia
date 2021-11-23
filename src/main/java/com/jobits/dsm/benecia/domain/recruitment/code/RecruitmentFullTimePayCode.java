package com.jobits.dsm.benecia.domain.recruitment.code;

import com.jobits.dsm.benecia.global.exception.AttributeConvertFailedException;
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
public enum RecruitmentFullTimePayCode {

    LSESS_2400("FPAY01", "22400만원 이하 400백"),
    MORE_2400("FPAY02", "2400만원 초과 2800만원 이하"),
    MORE_2800("FPAY03", "2800만원 초과 3200만원 이하"),
    MORE_3200("FPAY04", "3200만원 초과");

    private final String code;
    private final String value;

    private static final Map<String, RecruitmentFullTimePayCode> map =
            Collections.unmodifiableMap(Arrays.stream(RecruitmentFullTimePayCode.values())
                    .collect(Collectors.toMap(RecruitmentFullTimePayCode::getCode, Function.identity())));

    public static RecruitmentFullTimePayCode find(String dbData) {
        return Optional.of(map.get(dbData))
                .orElseThrow(AttributeConvertFailedException::new);
    }

    @Converter
    public static class RecruitmentFullTimePayCodeConverter implements AttributeConverter<RecruitmentFullTimePayCode, String> {

        @Override
        public String convertToDatabaseColumn(RecruitmentFullTimePayCode attribute) {
            return attribute.getCode();
        }

        @Override
        public RecruitmentFullTimePayCode convertToEntityAttribute(String dbData) {
            return Optional.of(RecruitmentFullTimePayCode.find(dbData))
                    .orElseThrow(AttributeConvertFailedException::new);
        }
    }
}
