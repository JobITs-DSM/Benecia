package com.jobits.dsm.benecia.domain.review.code;

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
public enum ReviewCode {
    TRAINING_REVIEW("REVW01", "실습후기"),
    INTERVIEW_REVIEW("REVW02", "면접후기");

    private final String code;
    private final String value;

    private static final Map<String, ReviewCode> MAP =
            Collections.unmodifiableMap(Arrays.stream(ReviewCode.values())
                    .collect(Collectors.toMap(ReviewCode::getCode, Function.identity())));

    public static ReviewCode find(String dbData) {
        return Optional.of(MAP.get(dbData))
                .orElseThrow(() -> AttributeConvertFailedException.EXCEPTION);
    }

    @Converter
    public static class ReviewCodeConverter implements AttributeConverter<ReviewCode, String> {

        @Override
        public String convertToDatabaseColumn(ReviewCode attribute) {
            return attribute.getCode();
        }

        @Override
        public ReviewCode convertToEntityAttribute(String dbData) {
            return find(dbData);
        }
    }
}