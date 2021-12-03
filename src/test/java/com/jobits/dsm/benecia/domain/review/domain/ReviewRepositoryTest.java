package com.jobits.dsm.benecia.domain.review.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @BeforeEach
    void cleanUp() {
        reviewRepository.deleteAll();
    }

    @Test
    void 저장_테스트_성공() {
        Integer tmp = 1;
        Review review = Review.builder()
                .id(tmp)
                .training_year("2004")
                .content("내용")
                .division()
    }
}
