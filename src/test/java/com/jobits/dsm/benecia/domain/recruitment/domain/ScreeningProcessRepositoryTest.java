package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.recruitment.code.ScreeningProcessCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.screeningprocess.ScreeningProcess;
import com.jobits.dsm.benecia.domain.recruitment.domain.screeningprocess.ScreeningProcessRepository;
import com.jobits.dsm.benecia.infrastructure.querydsl.QuerydslConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import(QuerydslConfig.class)
public class ScreeningProcessRepositoryTest {

    @Autowired
    private ScreeningProcessRepository screeningProcessRepository;

    @BeforeEach
    void cleanUp() {
        screeningProcessRepository.deleteAll();
    }

    @Test
    void 저장_성공_테스트() {
        ScreeningProcess screeningProcess = ScreeningProcess.builder()
                .code(ScreeningProcessCode.CODING_TEST)
                .procedure(1)
                .build();
        assertThat(screeningProcessRepository.save(screeningProcess).getCode()).isEqualTo(ScreeningProcessCode.CODING_TEST);
    }

    @Test
    void 저장_실패_테스트_Size_초과() {
        ScreeningProcess screeningProcess = ScreeningProcess.builder()
                .code(ScreeningProcessCode.CODING_TEST)
                .procedure(256)
                .build();
        assertThrows(DataIntegrityViolationException.class, () -> screeningProcessRepository.save(screeningProcess));
    }
}
