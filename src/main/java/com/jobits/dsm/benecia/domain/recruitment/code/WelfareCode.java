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
public enum WelfareCode {

    DORMITORY("WEL001", "기숙사 제공"),
    MEAL("WEL002", "중식 제공"),
    LAUNCH("WEL003", "석식 제공"),
    SELF_DEVELOP_COST("WEL004", "자기계발비"),
    ARMY_EXCEPTION_REQUEST("WEL005", "병특 신청"),
    YOUTH_RECRUITMENT("WEL006", "청년내일채움공채");

    private final String code;
    private final String value;

    private static final Map<String, WelfareCode> MAP =
            Collections.unmodifiableMap(Arrays.stream(WelfareCode.values())
                    .collect(Collectors.toMap(WelfareCode::getCode, Function.identity())));

    public static WelfareCode find(String dbData) {
        return Optional.of(MAP.get(dbData))
                .orElseThrow(AttributeConvertFailedException::new);
    }

    @Converter
    public static class WelfareCodeConverter implements AttributeConverter<WelfareCode, String> {

        @Override
        public String convertToDatabaseColumn(WelfareCode attribute) {
            return attribute.getCode();
        }

        @Override
        public WelfareCode convertToEntityAttribute(String dbData) {
            return find(dbData);
        }
    }
}
