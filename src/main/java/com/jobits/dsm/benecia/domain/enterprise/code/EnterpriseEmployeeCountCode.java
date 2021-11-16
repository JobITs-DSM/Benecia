package com.jobits.dsm.benecia.domain.enterprise.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnterpriseEmployeeCountCode {

    FIVE_PEOPLE_OR_LESS("EMCNT1", "5인 이하"),
    SIX_OR_MORE_AND_TWENTY_OR_LESS("EMCNT2", "6인 이상 20인 이하"),
    TWENTY_OR_MORE_AND_FIFTY_OR_LESS("EMCNT3", "21인 이상 50인 이하"),
    FIFTY_OR_MORE("EMCNT4", "50인 이상");

    private final String code;
    private final String value;
}