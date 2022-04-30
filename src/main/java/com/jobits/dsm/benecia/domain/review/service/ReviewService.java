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
import com.jobits.dsm.benecia.domain.student.facade.StudentFacade;
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
}
