package com.jobits.dsm.benecia.domain.recruitment.code;

import com.jobits.dsm.benecia.global.error.exception.AttributeConvertFailedException;
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
public enum RecruitmentStatusCode {
    RECRUITMENT_REQUEST("STAT01", "접수요청"),
    RECRUITING("STAT02", "모집중"),
    RECRUITED("STAT03", "모집완료");

    private final String code;
    private final String value;

    private static final Map<String, RecruitmentStatusCode> MAP =
            Collections.unmodifiableMap(Arrays.stream(RecruitmentStatusCode.values())
                    .collect(Collectors.toMap(RecruitmentStatusCode::getCode, Function.identity())));

    public static RecruitmentStatusCode find(String dbData) {
        return Optional.of(MAP.get(dbData))
                .orElseThrow(AttributeConvertFailedException::new);
    }

    @Converter
    public static class RecruitmentStatusCodeConverter implements AttributeConverter<RecruitmentStatusCode, String> {

        @Override
        public String convertToDatabaseColumn(RecruitmentStatusCode attribute) {
            return attribute.getCode();
        }

        @Override
        public RecruitmentStatusCode convertToEntityAttribute(String dbData) {
            return find(dbData);
        }
    }
}
