package com.jobits.dsm.benecia.domain.review.service;

import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import com.jobits.dsm.benecia.domain.enterprise.domain.EnterpriseRepository;
import com.jobits.dsm.benecia.domain.enterprise.exceptions.EnterpriseNotFoundException;
import com.jobits.dsm.benecia.domain.review.code.ReviewCode;
import com.jobits.dsm.benecia.domain.review.domain.Review;
import com.jobits.dsm.benecia.domain.review.domain.ReviewRepository;
import com.jobits.dsm.benecia.domain.review.presentation.payload.request.RegisterTrainingReviewRequest;
import com.jobits.dsm.benecia.domain.review.presentation.payload.response.QueryEnterpriseReviewForStudent;
import com.jobits.dsm.benecia.domain.student.domain.Student;
import com.jobits.dsm.benecia.domain.student.domain.StudentRepository;
import com.jobits.dsm.benecia.domain.student.exceptions.StudentNotFoundException;
import com.jobits.dsm.benecia.domain.student.facade.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final StudentRepository studentRepository;
    private final StudentFacade studentFacade;

    @Transactional
    public void registerTrainingReviewForStudent(String registrationNumber, RegisterTrainingReviewRequest request) {
        Student student = studentFacade.getCurrentUser();
        LocalDateTime now = LocalDateTime.now();
        Enterprise enterprise = enterpriseRepository.findById(registrationNumber)
                .orElseThrow(() -> EnterpriseNotFoundException.EXCEPTION);
        Review review = Review.builder()
                .trainingYear(String.valueOf(now.getYear()))
                .content(request.getContent())
                .division(request.getDivision())
                .registrationDateTime(now)
                .isConfirmed(false)
                .enterprise(enterprise)
                .student(student)
                .build();
        reviewRepository.save(review);
    }

    @Transactional(readOnly = true)
    public QueryEnterpriseReviewForStudent queryEnterpriseReviewForStudent(String registrationNumber) {
        Enterprise enterprise = enterpriseRepository.findById(registrationNumber)
                .orElseThrow(() -> EnterpriseNotFoundException.EXCEPTION);

        List<QueryEnterpriseReviewForStudent.ReviewInfo> interviewReviews = new ArrayList<>();
        List<QueryEnterpriseReviewForStudent.ReviewInfo> trainingReviews = new ArrayList<>();

        List<Review> reviews = reviewRepository.findByEnterprise(enterprise);

        for (Review review : reviews) {
            if (review.getDivision() == ReviewCode.INTERVIEW_REVIEW) {
                interviewReviews.add(QueryEnterpriseReviewForStudent.ReviewInfo.builder()
                        .userName(review.getStudent().getName())
                        .userProfileImageUrl(review.getStudent().getProfileImage().getFileName())
                        .content(review.getContent())
                        .build()
                );
            } else if (review.getDivision() == ReviewCode.TRAINING_REVIEW) {
                trainingReviews.add(QueryEnterpriseReviewForStudent.ReviewInfo.builder()
                        .userName(review.getStudent().getName())
                        .userProfileImageUrl(review.getStudent().getProfileImage().getFileName())
                        .content(review.getContent())
                        .build()
                );
            }
        }

        return QueryEnterpriseReviewForStudent.builder()
                .interviewReviewList(interviewReviews)
                .trainingReviewList(trainingReviews)
                .build();
    }

    @Transactional
    public void checkEnterpriseReviewForTeacher(Integer reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow();
        review.updateIsConfirmed(true);
    }
}
