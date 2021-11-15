package com.jobits.dsm.benecia.domain.recruitment.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WelfareCode {

    DORMITORY("WEL001", "기숙사 제공"),
    MEAL("WEL002", "식사 제공"),
    LAUNCH("WEL003", "중식 제공"),
    SELF_DEVELOP_COST("WEL004", "자기계발비"),
    ARMY_EXCEPTION_REQUEST("WEL005", "병특 신청"),
    YOUTH_RECRUITMENT("WEL006", "청년내일채움공채");

    private final String code;
    private final String value;

}
