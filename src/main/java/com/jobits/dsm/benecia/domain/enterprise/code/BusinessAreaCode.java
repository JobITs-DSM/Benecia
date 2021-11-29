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
public enum BusinessAreaCode {
    WEB_SERVICE("DIV001", "웹 서비스"),
    MOBILE_SERVICE("DIV002", "모바일 서비스"),
    E_COMMERCE("DIV003", "전자 상거래"),
    SOLUTION("DIV004", "솔루션"),
    ENTERPRISE_RESOURCE_MANAGEMENT("DIV005", "전사적 자원 관리"),
    DELIVERY_ORDER_SERVICE("DIV006", "배달 주문 서비스"),
    EDU_TECH("DIV007", "교육"),
    GAME("DIV008", "게임"),
    SECURITY("DIV009", "보안"),
    CONTENT_CREATION("DIV010", "컨텐츠 제작"),
    IOT_AND_ROBOT("DIV011", "IOT/로봇"),
    FIRMWARE_AND_EQUIPMENT_CONTROL("DIV012", "펌웨어/장비제어"),
    MEDICAL_TREATMENT("DIV013", "의료"),
    ASSET_MANAGEMENT("DIV014", "자산 관리"),
    FIN_TECH("DIV015", "핀테크"),
    AI("DIV016", "인공지능"),
    BLOCK_CHAIN("DIV017", "블록체인"),
    BIG_DATA("DIV018", "빅데이터"),
    AEROSPACE("DIV019", "우주항공");

    private final String code;
    private final String value;

    private static final Map<String, BusinessAreaCode> map =
            Collections.unmodifiableMap(Arrays.stream(BusinessAreaCode.values())
                .collect(Collectors.toMap(BusinessAreaCode::getCode, Function.identity())));

    public static BusinessAreaCode find(String dbData) {
        return Optional.of(map.get(dbData))
                .orElseThrow(AttributeConvertFailedException::new);
    }

    @Converter
    public static class BusinessAreaCodeConverter implements AttributeConverter<BusinessAreaCode, String> {

        @Override
        public String convertToDatabaseColumn(BusinessAreaCode attribute) {
            return attribute.getCode();
        }

        @Override
        public BusinessAreaCode convertToEntityAttribute(String dbData) {
            return Optional.of(BusinessAreaCode.find(dbData))
                    .orElseThrow(AttributeConvertFailedException::new);
        }
    }
}