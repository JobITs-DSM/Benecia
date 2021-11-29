package com.jobits.dsm.benecia.domain.enterprise.code;

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
public enum EnterpriseDivisionCode {

    PARTICIPATE_COMPANY("ENTE01", "참여기업"),
    LEADING_COMPANY("ENTE02", "선도기업");

    private final String code;
    private final String value;

    private static final Map<String, EnterpriseDivisionCode> MAP =
            Collections.unmodifiableMap(Arrays.stream(EnterpriseDivisionCode.values())
                    .collect(Collectors.toMap(EnterpriseDivisionCode::getCode, Function.identity())));

    public static EnterpriseDivisionCode find(String dbData) {
        return Optional.of(MAP.get(dbData))
                .orElseThrow(AttributeConvertFailedException::new);
    }

    @Converter
    public static class EnterpriseDivisionCodeConverter implements AttributeConverter<EnterpriseDivisionCode, String> {

        @Override
        public String convertToDatabaseColumn(EnterpriseDivisionCode attribute) {
            return attribute.getCode();
        }

        @Override
        public EnterpriseDivisionCode convertToEntityAttribute(String dbData) {
            return find(dbData);
        }
    }

}
