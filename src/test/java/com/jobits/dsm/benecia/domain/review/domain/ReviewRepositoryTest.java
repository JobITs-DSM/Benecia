package com.jobits.dsm.benecia.domain.review.domain;

import com.jobits.dsm.benecia.domain.review.code.ReviewCode;
import com.jobits.dsm.benecia.infrastructure.querydsl.QuerydslConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @BeforeEach
    void cleanUp() {
        reviewRepository.deleteAll();
    }

    @Test
    void 저장_테스트_성공() {
        Review review = Review.builder()
                .trainingYear("2004")
                .content("내용")
                .division(ReviewCode.INTERVIEW_REVIEW)
                .registrationDateTime(LocalDateTime.now())
                .build();
        assertThat(reviewRepository.save(review).getDivision()).isEqualTo(ReviewCode.INTERVIEW_REVIEW);

    }
}
