package com.jobits.dsm.benecia.domain.application.code;

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
public enum ApplicationCode {

    UNDER_REVIEW("APL1", "지원서 검토중"),
    SENT("APL2", "지원서 전송 완료"),
    APPLICATION_COMPLETE("APL3", "지원 완료");

    private final String code;
    private final String value;

    private static final Map<String, ApplicationCode> MAP =
            Collections.unmodifiableMap(Arrays.stream(ApplicationCode.values())
                    .collect(Collectors.toMap(ApplicationCode::getCode, Function.identity())));

    public static ApplicationCode find(String dbData) {
        return Optional.of(MAP.get(dbData))
                .orElseThrow(() -> AttributeConvertFailedException.EXCEPTION);
    }

    @Converter
    public static class ApplicationCodeConverter implements AttributeConverter<ApplicationCode, String> {
        @Override
        public String convertToDatabaseColumn(ApplicationCode attribute) {
            return attribute.getCode();
        }

        @Override
        public ApplicationCode convertToEntityAttribute(String dbData) {
            return find(dbData);
        }
    }
}
