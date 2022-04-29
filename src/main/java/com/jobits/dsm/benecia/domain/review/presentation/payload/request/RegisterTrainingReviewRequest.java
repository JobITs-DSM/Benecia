package com.jobits.dsm.benecia.domain.review.presentation.payload.request;

import com.jobits.dsm.benecia.domain.review.code.ReviewCode;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class RegisterTrainingReviewRequest {
    @NotEmpty
    private ReviewCode division;
    @NotEmpty
    private String content;
}
