package com.jobits.dsm.benecia.domain.review.service;

import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import com.jobits.dsm.benecia.domain.enterprise.domain.EnterpriseRepository;
import com.jobits.dsm.benecia.domain.enterprise.exceptions.EnterpriseNotFoundException;
import com.jobits.dsm.benecia.domain.review.domain.Review;
import com.jobits.dsm.benecia.domain.review.domain.ReviewRepository;
import com.jobits.dsm.benecia.domain.review.presentation.payload.RegisterTrainingReviewRequest;
import com.jobits.dsm.benecia.domain.student.domain.Student;
import com.jobits.dsm.benecia.domain.student.domain.StudentRepository;
import com.jobits.dsm.benecia.domain.student.exceptions.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public void registerTrainingReview(String registrationNumber, String studentId, RegisterTrainingReviewRequest request) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
        Enterprise enterprise = enterpriseRepository.findById(registrationNumber)
                .orElseThrow(() -> EnterpriseNotFoundException.EXCEPTION);
        Review review = Review.builder()
                .trainingYear(String.valueOf(localDateTime.getYear()))
                .content(request.getContent())
                .division(request.getDivision())
                .registrationDateTime(localDateTime)
                .enterprise(enterprise)
                .student(student)
                .build();
        reviewRepository.save(review);
    }
}
