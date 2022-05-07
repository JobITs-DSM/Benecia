package com.jobits.dsm.benecia.domain.review.exceptions;

import com.jobits.dsm.benecia.global.error.exception.GlobalException;


public class ReviewNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new ReviewNotFoundException();

    private ReviewNotFoundException() {
        super(ReviewErrorCode.REVIEW_NOT_FOUND);
    }
}
