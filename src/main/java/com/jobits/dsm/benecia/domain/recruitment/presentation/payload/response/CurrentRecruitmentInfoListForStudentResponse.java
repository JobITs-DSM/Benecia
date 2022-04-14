package com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CurrentRecruitmentInfoListForStudentResponse {
    private final List<CurrentRecruitmentInfo> recruitments;

    @Getter
    @Builder
    public static class CurrentRecruitmentInfo {
        private final String hiring;
        private final Integer recruitCount;
        private final String enterpriseName;
        private final String workPlace;
        private final List<String> tags;
        private final String enterpriseProfileImageUrl;
        private final String enterpriseBackgroundImageUrl;
    }
}