package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea.HiringArea;
import com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea.HiringAreaRepository;
import com.jobits.dsm.benecia.infrastructure.querydsl.QuerydslConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import(QuerydslConfig.class)
public class HiringRepositoryTest {

    @Autowired
    private HiringAreaRepository hiringAreaRepository;

    @BeforeEach
    void cleanUp() {
        hiringAreaRepository.deleteAll();
    }

    @Test
    void 저장_성공_테스트() {
        HiringArea hiringArea = HiringArea.builder()
                .code(HiringAreaCode.AI)
                .task("인공지능")
                .build();
        assertThat(hiringAreaRepository.save(hiringArea).getCode()).isEqualTo(HiringAreaCode.AI);
    }
}
