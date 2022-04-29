package com.jobits.dsm.benecia.domain.review.presentation;

import com.jobits.dsm.benecia.domain.review.presentation.payload.request.RegisterTrainingReviewRequest;
import com.jobits.dsm.benecia.domain.review.presentation.payload.response.QueryEnterpriseReviewForStudent;
import com.jobits.dsm.benecia.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/{registration-number}/{student-id}")
    public void registerTrainingReview(@PathVariable("registration-number") String registrationNumber,
                                                 @PathVariable("student-id") String studentId,
                                                 @RequestBody @Valid RegisterTrainingReviewRequest request) {
        reviewService.registerTrainingReview(registrationNumber, studentId, request);
    }

    @GetMapping("/{registration-number}")
    public QueryEnterpriseReviewForStudent queryEnterpriseReviewForStudent(@PathVariable("registration-number") String registrationNumber) {
        return reviewService.queryEnterpriseReviewForStudent(registrationNumber);
    }
}
