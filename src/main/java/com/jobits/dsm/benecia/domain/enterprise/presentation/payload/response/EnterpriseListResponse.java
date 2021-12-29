package com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response;

import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseDivisionCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseEmployeeCountCode;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class EnterpriseListResponse {
    private final List<EnterpriseInfo> enterprises;

    @Getter
    @Builder
    public static class EnterpriseInfo {
        private final String registrationNumber;
        private final String name;
        private final String postalCode;
        private final EnterpriseEmployeeCountCode employeeCount;
        private final Integer turnover;
        private final EnterpriseDivisionCode division;
        private final Boolean isConvention;
        private final List<String> businessAreas;
        private final Integer lastReceptionYear;
        private final Long contractStudentCount;
        private final Long reviewCount;
    }

    public static EnterpriseInfo of(
            String registrationNumber,
            String name,
            String postalCode,
            EnterpriseEmployeeCountCode employeeCount,
            Integer turnover,
            EnterpriseDivisionCode division,
            Boolean isConvention,
            List<String> businessAreas,
            Integer lastReceptionYear,
            Long contractStudentCount,
            Long reviewCount
            ) {
        return new EnterpriseInfo(registrationNumber, name, postalCode, employeeCount, turnover, division, isConvention, businessAreas, lastReceptionYear, contractStudentCount, reviewCount);
    }

}
