package com.jobits.dsm.benecia.domain.student.code;

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
public enum DepartmentCode {
    JUNIOR_SOFTWARE_FIRST("DE0101", "2-소프트웨어개발과-1"),
    JUNIOR_SOFTWARE_SECOND("DE0102", "2-소프트웨어개발과-2"),
    JUNIOR_EMBEDDED("DE0103", "2-임베디드소프트웨어과"),
    JUNIOR_SECURITY("DE0104", "2-정보보안과"),
    SENIOR_SOFTWARE_FIRST("DE0105", "3-소프트웨어개발과-1"),
    SENIOR_SOFTWARE_SECOND("DE0106", "3-소프트웨어개발과-2"),
    SENIOR_EMBEDDED("DE0107", "3-임베디드소프트웨어과"),
    SENIOR_SECURITY("DE0108", "3-정보보안과");

    private final String code;
    private final String value;

    private static final Map<String, DepartmentCode> MAP =
            Collections.unmodifiableMap(Arrays.stream(DepartmentCode.values())
                    .collect(Collectors.toMap(DepartmentCode::getCode, Function.identity())));

    public static DepartmentCode find(String dbData) {
        return Optional.of(MAP.get(dbData))
                .orElseThrow(() -> AttributeConvertFailedException.EXCEPTION);
    }

    @Converter
    public static class DepartmentCodeConverter implements AttributeConverter<DepartmentCode, String> {

        @Override
        public String convertToDatabaseColumn(DepartmentCode attribute) {
            return attribute.getCode();
        }

        @Override
        public DepartmentCode convertToEntityAttribute(String dbData) {
            return find(dbData);
        }
    }
}
