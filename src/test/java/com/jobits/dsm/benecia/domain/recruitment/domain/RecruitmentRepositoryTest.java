package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
class RecruitmentRepositoryTest {

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @BeforeEach
    void cleanUp() {
        recruitmentRepository.deleteAll();
    }

    @Test
    void 저장_테스트_성공() {
        RecruitmentId recruitmentId = new RecruitmentId("2021", "123456789012");
        Recruitment recruitment = Recruitment.builder()
                .recruitmentId(recruitmentId)
                .status(RecruitmentStatusCode.RECRUITMENT_REQUEST)
                .build();

        assertThat(recruitmentRepository.save(recruitment).getRecruitmentId()).isEqualTo(recruitmentId);
    }

    @Test
    void 저장_테스트_실패_SIZE_초과() {
        RecruitmentId recruitmentId = new RecruitmentId("202111", "123456789012");
        Recruitment recruitment = Recruitment.builder()
                .recruitmentId(recruitmentId)
                .status(RecruitmentStatusCode.RECRUITMENT_REQUEST)
                .build();

        assertThrows(DataIntegrityViolationException.class, () -> recruitmentRepository.saveAndFlush(recruitment));
    }

}