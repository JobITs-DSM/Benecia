package com.jobits.dsm.benecia.domain.recruitment.domain.vo;

import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class AllRecruitmentInfoListForStudentVO {
    private final Long count;
    private final HiringAreaCode hiring;
    private final Integer recruitCount;
    private final String enterpriseName;
    private final String introduction;
    private final List<String> tags;
    private final String enterpriseProfileImageUrl;
    private final LocalDate recruitEndDate;

    @QueryProjection
    public AllRecruitmentInfoListForStudentVO(Long count, HiringAreaCode hiring, Integer recruitCount, String enterpriseName, String introduction, List<String> tags, String enterpriseProfileImageUrl, LocalDate recruitEndDate) {
        this.count = count;
        this.hiring = hiring;
        this.recruitCount = recruitCount;
        this.enterpriseName = enterpriseName;
        this.introduction = introduction;
        this.tags = tags;
        this.enterpriseProfileImageUrl = enterpriseProfileImageUrl;
        this.recruitEndDate = recruitEndDate;
    }
}
