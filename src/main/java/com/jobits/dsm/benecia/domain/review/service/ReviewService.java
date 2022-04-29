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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public void registerTrainingReview(String registrationNumber, String studentId, RegisterTrainingReviewRequest request) {
        LocalDateTime now = LocalDateTime.now();
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
        Enterprise enterprise = enterpriseRepository.findById(registrationNumber)
                .orElseThrow(() -> EnterpriseNotFoundException.EXCEPTION);
        Review review = Review.builder()
                .trainingYear(String.valueOf(now.getYear()))
                .content(request.getContent())
                .division(request.getDivision())
                .registrationDateTime(now)
                .enterprise(enterprise)
                .student(student)
                .build();
        reviewRepository.save(review);
    }

    @Transactional(readOnly = true)
    public QueryEnterpriseReviewForStudent queryEnterpriseReviewForStudent(String registrationNumber) {
        Enterprise enterprise = enterpriseRepository.findById(registrationNumber)
                        .orElseThrow(() -> EnterpriseNotFoundException.EXCEPTION);
        List<QueryEnterpriseReviewForStudent.ReviewInfo> interviewReview = reviewRepository.findByEnterpriseAndDivision(enterprise, ReviewCode.INTERVIEW_REVIEW)
                .stream().map(interviewReviews -> QueryEnterpriseReviewForStudent.ReviewInfo.builder()
                        .userName(interviewReviews.getStudent().getName())
                        .userProfileImageUrl(interviewReviews.getStudent().getProfileImage().getFileName())
                        .content(interviewReviews.getContent())
                        .build()
                ).toList();

        List<QueryEnterpriseReviewForStudent.ReviewInfo> trainingReview = reviewRepository.findByEnterpriseAndDivision(enterprise, ReviewCode.TRAINING_REVIEW)
                .stream().map(trainingReviews -> QueryEnterpriseReviewForStudent.ReviewInfo.builder()
                        .userName(trainingReviews.getStudent().getName())
                        .userProfileImageUrl(trainingReviews.getStudent().getProfileImage().getFileName())
                        .content(trainingReviews.getContent())
                        .build()
                ).toList();

        return QueryEnterpriseReviewForStudent.builder()
                .interviewReviewList(interviewReview)
                .trainingReviewList(trainingReview)
                .build();
    }
}
