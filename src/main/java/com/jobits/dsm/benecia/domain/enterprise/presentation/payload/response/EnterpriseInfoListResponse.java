package com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response;

import com.jobits.dsm.benecia.domain.enterprise.code.BusinessAreaCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseDivisionCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseEmployeeCountCode;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class EnterpriseInfoListResponse {
    private final List<EnterpriseInfo> enterprises;

    @Getter
    @Builder
    public static class EnterpriseInfo {
        private final String registrationNumber;
        private final String name;
        private final EnterpriseEmployeeCountCode employeeCount;
        private final Integer turnover;
        private final EnterpriseDivisionCode division;
        private final Boolean isConvention;
        private final BusinessAreaCode businessArea;
        private final Integer lastReceptionYear;
        private final Long contractStudentCount;
        private final Long reviewCount;
    }

}
