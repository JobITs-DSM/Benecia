package com.jobits.dsm.benecia.domain.student.domain.vo;

import com.jobits.dsm.benecia.domain.application.code.ApplicationCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseEmployeeCountCode;
import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentCurrentStatusVO {
    private final String userName;
    private final String userProfileImageUrl;
    private final ApplicationCode employmentStatus;
    private final String enterpriseImageUrl;
    private final HiringAreaCode field;

    @QueryProjection
    public StudentCurrentStatusVO(String userName, String userProfileImageUrl, ApplicationCode employmentStatus, String enterpriseImageUrl, HiringAreaCode field) {
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.employmentStatus = employmentStatus;
        this.enterpriseImageUrl = enterpriseImageUrl;
        this.field = field;
    }
}
