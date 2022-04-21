package com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response;

import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SimilarRecruitmentInfoListForStudentResponse {
    private final HiringInfo hiringAreas;
    private final Integer recruitCount;
    private final String enterpriseName;
    private final String workPlace;
    private final List<String> tags;
    private final String enterpriseProfileImageUrl;
    private final String enterpriseBackgroundImageUrl;

    @Getter
    @Builder
    public static class HiringInfo {
        private final Integer id;
        private final HiringAreaCode code;
    }
}
