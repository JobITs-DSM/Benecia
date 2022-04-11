package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentFullTimePayCode;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;
import com.jobits.dsm.benecia.infrastructure.querydsl.QuerydslConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
@Import(QuerydslConfig.class)
class RecruitmentRepositoryTest {

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @BeforeEach
    void cleanUp() {
        recruitmentRepository.deleteAll();
    }

//    @Test
    void 저장_테스트_성공() {
        RecruitmentId recruitmentId = new RecruitmentId("2021", "123456789012");
        Recruitment recruitment = Recruitment.builder()
                .recruitmentId(recruitmentId)
                .status(RecruitmentStatusCode.RECRUITMENT_REQUEST)
                .documentation(new Documentation("docu1", "docu2", "docu3"))
                .printDateTime(null)
                .form(new Form(null, null, null))
                .fullTimePay(RecruitmentFullTimePayCode.LESS_2400)
                .otherLanguage("Languages")
                .otherSpecifics("Specifics")
                .otherTechnology(null)
                .recruitmentDate(RecruitmentDate.builder()
                        .recruitEndDate(LocalDate.of(2020, 9, 10))
                        .recruitBeginDate(LocalDate.of(2020, 9, 5))
                        .requestBeginDate(LocalDate.of(2020, 9, 1))
                        .build())
                .preferential("preferential")
                .report(1)
                .build();

        assertThat(recruitmentRepository.save(recruitment).getRecruitmentId()).isEqualTo(recruitmentId);
    }

//    @Test
    void 저장_테스트_실패_SIZE_초과() {
        RecruitmentId recruitmentId = new RecruitmentId("202111", "123456789012");
        Recruitment recruitment = Recruitment.builder()
                .recruitmentId(recruitmentId)
                .status(RecruitmentStatusCode.RECRUITMENT_REQUEST)
                .build();

        assertThrows(ConstraintViolationException.class, () -> recruitmentRepository.saveAndFlush(recruitment));
    }

}