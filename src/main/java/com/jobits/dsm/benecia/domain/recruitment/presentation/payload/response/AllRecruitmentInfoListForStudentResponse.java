package com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response;

import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class AllRecruitmentInfoListForStudentResponse {
    private final List<AllRecruitmentInfo> recruitments;

    @Getter
    @Builder
    public static class AllRecruitmentInfo {
        private final HiringInfo hiring;
        private final Integer recruitCount;
        private final String enterpriseName;
        private final String introduction;
        private final List<String> tags;
        private final String enterpriseProfileImageUrl;
        private final LocalDate recruitEndDate;
    }

    @Getter
    @Builder
    public static class HiringInfo {
        private final Integer id;
        private final HiringAreaCode code;
    }
}
