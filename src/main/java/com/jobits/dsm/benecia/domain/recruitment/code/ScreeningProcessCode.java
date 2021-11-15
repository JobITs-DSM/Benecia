package com.jobits.dsm.benecia.domain.recruitment.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ScreeningProcessCode {

    DOCUMENT("PROC01", "서류전형"),
    PERSONALITY("PROC02", "인적성 테스트"),
    CODING_TEST("PROC03", "코딩 테스트"),
    LIVE_CODING("PROC04", "라이브 코딩"),
    AI_INTERVIEW("PROC05", "AI 면접"),
    ASSIGNMENT("PROC06", "과제 제출"),
    TECHNIQUE_INTERVIEW("PROC07", "기술 면접"),
    CULTURE_INTERVIEW("PROC08", "컬쳐 면접"),
    EXECUTIVE_INTERVIEW("PROC09", "임원 면접"),
    FINAL_INTERVIEW("PROC10", "최종 면접");

    private final String code;
    private final String value;

}
