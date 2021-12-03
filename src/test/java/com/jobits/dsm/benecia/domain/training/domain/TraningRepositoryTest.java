package com.jobits.dsm.benecia.domain.training.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class TraningRepositoryTest {

    @Autowired
    private TrainingRepository trainingRepository;

    @BeforeEach
    void cleanUp() {
        trainingRepository.deleteAll();
    }

    @Test
    void 저장_테스트_성공() {
        Integer tmp = 1;
        Training training = Training.builder()
                .id(tmp)
                .begin_date_time(LocalDateTime.now())
                .end_date_time(null)
                .build();
        assertThat(trainingRepository.save(training).getId()).isEqualTo(tmp);
    }
}
