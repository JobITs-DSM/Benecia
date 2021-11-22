package com.jobits.dsm.benecia.domain.enterprise.code;

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

}
