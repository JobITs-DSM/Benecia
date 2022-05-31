package com.jobits.dsm.benecia.domain.enterprise.domain;

import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseDivisionCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseEmployeeCountCode;
import com.jobits.dsm.benecia.infrastructure.querydsl.QuerydslConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Import(QuerydslConfig.class)
@DataJpaTest
class EnterpriseRepositoryTest {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @BeforeEach
    void cleanUp() {
        enterpriseRepository.deleteAll();
    }

    @Test
    void 저장_테스트_성공() {
        String tmp = "000-00-00000";

        Enterprise enterprise = getEnterprise(tmp);

        assertThat(enterpriseRepository.save(enterprise).getRegistrationNumber()).isEqualTo(tmp);
    }

    @Test
    void 저장_테스트_실패_SIZE_초과() {
        String tmp = "000-0000-00000";

        Enterprise enterprise = getEnterprise(tmp);

        assertThrows(ConstraintViolationException.class, () -> enterpriseRepository.saveAndFlush(enterprise));
    }

    private Enterprise getEnterprise(String registrationNumber) {
        Director director = Director.builder()
                .email("director@gmail.com")
                .name("박상우")
                .telephoneNumber("01012345678")
                .department("팀장")
                .build();

        return Enterprise.builder()
                .registrationNumber(registrationNumber)
                .name("잡플래닛")
                .establishYear("2004")
                .isConvention(true)
                .division(EnterpriseDivisionCode.LEADING_COMPANY)
                .representativeName("박상우")
                .address("서울특별시")
                .introduction("회사 소개")
                .employeeCount(EnterpriseEmployeeCountCode.FIFTY_OR_MORE)
                .site("url")
                .turnover(128394)
                .director(director)
                .build();
    }
}
