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

    @PostMapping("/{registration-number}")
    public void registerTrainingReviewForStudent(@PathVariable("registration-number") String registrationNumber,
                                                 @RequestBody @Valid RegisterTrainingReviewRequest request) {
        reviewService.registerTrainingReviewForStudent(registrationNumber, request);
    }

    @GetMapping("/{registration-number}")
    public QueryEnterpriseReviewForStudent queryEnterpriseReviewForStudent(@PathVariable("registration-number") String registrationNumber) {
        return reviewService.queryEnterpriseReviewForStudent(registrationNumber);
    }
}
