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
public enum EnterpriseEmployeeCountCode {

    FIVE_PEOPLE_OR_LESS("EMCNT1", "5인 이하"),
    SIX_OR_MORE_AND_TWENTY_OR_LESS("EMCNT2", "6인 이상 20인 이하"),
    TWENTY_OR_MORE_AND_FIFTY_OR_LESS("EMCNT3", "21인 이상 50인 이하"),
    FIFTY_OR_MORE("EMCNT4", "50인 이상");

    private final String code;
    private final String value;

    private static final Map<String, EnterpriseEmployeeCountCode> MAP =
            Collections.unmodifiableMap(Arrays.stream(EnterpriseEmployeeCountCode.values())
                    .collect(Collectors.toMap(EnterpriseEmployeeCountCode::getCode, Function.identity())));

    public static EnterpriseEmployeeCountCode find(String dbData) {
        return Optional.of(MAP.get(dbData))
                .orElseThrow(AttributeConvertFailedException::new);
    }

    @Converter
    public static class EnterpriseEmployeeCountCodeConverter implements AttributeConverter<EnterpriseEmployeeCountCode, String> {

        @Override
        public String convertToDatabaseColumn(EnterpriseEmployeeCountCode attribute) {
            return attribute.getCode();
        }

        @Override
        public EnterpriseEmployeeCountCode convertToEntityAttribute(String dbData) {
            return find(dbData);
        }
    }
}