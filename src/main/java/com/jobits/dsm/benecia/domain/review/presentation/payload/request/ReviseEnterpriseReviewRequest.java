package com.jobits.dsm.benecia.domain.review.presentation.payload.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class ReviseEnterpriseReviewRequest {
    @NotEmpty
    private String content;
}
