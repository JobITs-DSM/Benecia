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
public enum ScreeningProcessCode {

    DOCUMENT("PROC01", "서류전형"),
    PERSONALITY("PROC02", "인적성 테스트"),
    CODING_TEST("PROC03", "코딩 테스트"),
    LIVE_CODING("PROC04", "라이브 코딩"),
    AI_INTERVIEW("PROC05", "AI 면접"),
    ASSIGNMENT("PROC06", "과제 제출"),
    TECHNIQUE_INTERVIEW("PROC07", "기술 면접"),
    CULTURE_INTERVIEW("PROC08", "컬쳐 면접"),
    EXECUTIVE_INTERVIEW("PROC09", "임원 면접"),
    FINAL_INTERVIEW("PROC10", "최종 면접");

    private final String code;
    private final String value;

    private static final Map<String, ScreeningProcessCode> MAP =
            Collections.unmodifiableMap(Arrays.stream(ScreeningProcessCode.values())
                    .collect(Collectors.toMap(ScreeningProcessCode::getCode, Function.identity())));

    public static ScreeningProcessCode find(String dbData) {
        return Optional.of(MAP.get(dbData))
                .orElseThrow(AttributeConvertFailedException::new);
    }

    @Converter
    public static class ScreeningProcessCodeConverter implements AttributeConverter<ScreeningProcessCode, String> {

        @Override
        public String convertToDatabaseColumn(ScreeningProcessCode attribute) {
            return attribute.getCode();
        }

        @Override
        public ScreeningProcessCode convertToEntityAttribute(String dbData) {
            return find(dbData);
        }
    }
}
