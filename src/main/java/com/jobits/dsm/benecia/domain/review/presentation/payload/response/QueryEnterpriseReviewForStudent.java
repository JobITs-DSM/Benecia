package com.jobits.dsm.benecia.domain.review.presentation.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QueryEnterpriseReviewForStudent {

    private final List<ReviewInfo> interviewReviewList;

    private final List<ReviewInfo> trainingReviewList;

    @Getter
    @Builder
    public static class ReviewInfo {

        private final String userName;

        private final String userProfileImageUrl;

        private final String content;
    }
}
