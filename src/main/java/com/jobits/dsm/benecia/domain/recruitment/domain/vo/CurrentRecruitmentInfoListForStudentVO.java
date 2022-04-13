package com.jobits.dsm.benecia.domain.recruitment.domain.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CurrentRecruitmentInfoListForStudentVO {
    private final String hiring;
    private final Integer recruitCount;
    private final String enterpriseName;
    private final String workPlace;
    private final List<String> tags;
    private final String enterpriseProfileImageUrl;
    private final String enterpriseBackgroundImageUrl;
}
