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
public enum HiringArea {

    FULL_STACK("TA0101", "풀스택", "웹프로그래밍"),
    FRONTEND("TA0102", "웹 프론트엔드", "웹프로그래밍"),
    BACKEND("TA0103", "웹 백엔드", "웹프로그래밍"),
    MOBILE_ALL("TA0201", "전체", "모바일"),
    MOBILE_ANDROID("TA0202", "안드로이드", "모바일"),
    MOBILE_IOS("TA0203", "iOS", "모바일"),
    EMBEDDED_ALL("TA0301", "전체", "임베디드"),
    EMBEDDED_FIRMWARE("TA0302", "펌웨어", "임베디드"),
    EMBEDDED_APPLICATIONS("TA0303", "응용프로그래밍", "임베디드"),
    EMBEDDED_SYSTEM("TA0304", "시스템프로그래밍", "임베디드"),
    GAME_FRONTEND("TA0401", "게임 프론트엔드", "게임"),
    GAME_BACKEND("TA0402", "게임 백엔드", "게임"),
    GAME_PLANNER("TA0403", "기획", "게임"),
    WINDOWS_PROGRAMMING("TA0501", "윈도우프로그래밍", "응용프로그래밍"),
    SYSTEM_PROGRAMMING("TA0502", "시스템프로그래밍", "응용프로그래밍"),
    SECURITY_PROGRAMMING("TA0601", "보안프로그래밍", "보안"),
    MOCK_HACKING("TA0602", "모의해킹/취약점분석", "보안"),
    AI("TA0701", "인공지능", "기타"),
    BIG_DATA("TA0702", "빅데이터", "기타"),
    BLOCK_CHAIN("TA0703", "블록체인", "기타");

    private final String code;
    private final String value;
    private final String category;

    private static final Map<String, HiringArea> MAP =
            Collections.unmodifiableMap(Arrays.stream(HiringArea.values())
                    .collect(Collectors.toMap(HiringArea::getCode, Function.identity())));

    public static HiringArea find(String dbData) {
        return Optional.of(MAP.get(dbData))
                .orElseThrow(AttributeConvertFailedException::new);
    }

    @Converter
    public static class HiringAreaConverter implements AttributeConverter<HiringArea, String> {

        @Override
        public String convertToDatabaseColumn(HiringArea attribute) {
            return attribute.getCode();
        }

        @Override
        public HiringArea convertToEntityAttribute(String dbData) {
            return find(dbData);
        }
    }
}
