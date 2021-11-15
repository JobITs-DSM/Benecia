package com.jobits.dsm.benecia.domain.recruitment.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ScreeningProcessCode {
    FULL_STACK("TA0101", "풀스택"),
    FRONTEND("TA0102", "웹 프론트엔드"),
    BACKEND("TA0103", "웹 백엔드"),
    MOBILE_ALL("TA0201", "모바일 전체"),
    MOBILE_ANDROID("TA0202", "안드로이드"),
    MOBILE_IOS("TA0203", "iOS"),
    EMBEDDED_ALL("TA0301", "임베디드 전체"),
    EMBEDDED_FIRMWARE("TA0302", "펌웨어"),
    EMBEDDED_APPLICATIONS("TA0303", "응용프로그래밍"),
    EMBEDDED_SYSTEM("TA0304", "시스템프로그래밍"),
    GAME_FRONTEND("TA0401", "게임 프론트엔드"),
    GAME_BACKEND("TA0402", "게임 백엔드"),
    GAME_PLANNER("TA0403", "기획"),
    WINDOWS_PROGRAMMING("TA0501", "윈도우프로그래밍"),
    SYSTEM_PROGRAMMING("TA0502", "시스템프로그래밍"),
    SECURITY_PROGRAMMING("TA0601", "보안프로그래밍"),
    MOCK_HACKING("TA0602", "모의해킹/취약점분석"),
    SCREENING_PROCESS_AI("TA0701", "인공지능"),
    BIG_DATA("TA0702", "빅데이터"),
    BLOCK_CHAIN("TA0703", "블록체인");

    private final String code;
    private final String value;

}
