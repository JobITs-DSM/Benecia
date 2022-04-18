package com.jobits.dsm.benecia.domain.recruitment.domain.vo;

import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CurrentRecruitmentInfoListForStudentVO {
    private final Long count;
    private final HiringAreaCode hiring;
    private final Integer recruitCount;
    private final String enterpriseName;
    private final String workPlace;
    private final List<String> tags;
    private final String enterpriseProfileImageUrl;
    private final String enterpriseBackgroundImageUrl;

    @QueryProjection
    public CurrentRecruitmentInfoListForStudentVO(Long count, HiringAreaCode hiring, Integer recruitCount, String enterpriseName, String workPlace, List<String> tags, String enterpriseProfileImageUrl, String enterpriseBackgroundImageUrl) {
        this.count = count;
        this.hiring = hiring;
        this.recruitCount = recruitCount;
        this.enterpriseName = enterpriseName;
        this.workPlace = workPlace;
        this.tags = tags;
        this.enterpriseProfileImageUrl = enterpriseProfileImageUrl;
        this.enterpriseBackgroundImageUrl = enterpriseBackgroundImageUrl;
    }
}
