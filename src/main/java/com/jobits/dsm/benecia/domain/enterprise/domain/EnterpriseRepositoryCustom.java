package com.jobits.dsm.benecia.domain.enterprise.domain;

import com.jobits.dsm.benecia.domain.enterprise.code.BusinessAreaCode;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.ModifyEnterpriseInfoRequest;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface EnterpriseRepositoryCustom {
    List<BusinessAreaCode> getBusinessAreas(String registrationNumber);

    Long getContractStudentCount(String registrationNumber);

    Long getReviewCount(String registrationNumber);
}
