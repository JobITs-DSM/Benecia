package com.jobits.dsm.benecia.domain.enterprise.domain;

import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseDivisionCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseEmployeeCountCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        Enterprise enterprise = Enterprise.builder()
                .registration_number(tmp)
                .name("잡플래닛")
                .establish_year("2004")
                .is_convention(true)
                .division(EnterpriseDivisionCode.LEADING_COMPANY)
                .representative_name("박상우")
                .headquarter(Headquarter.builder()
                        .postal_code("11111")
                        .address("headquarter_address")
                        .address_detail("headquarter_address_detail")
                        .build()
                )
                .branch(Branch.builder()
                        .branch_postal_code("11111")
                        .branch_address("branch_address")
                        .branch_address_detail("branch_address_detail")
                        .build()
                )
                .introduction("회사 소개")
                .employee_count(EnterpriseEmployeeCountCode.FIFTY_OR_MORE)
                .site("url")
                .turnover(128394)
                .director(Director.builder()
                        .director_email("director@gmail.com")
                        .director_name("박상우")
                        .director_telephone_number("01012345678")
                        .director_phone_number("01023462123")
                        .director_department("팀장")
                        .build()
                )
                .build();
        assertThat(enterpriseRepository.save(enterprise).getRegistration_number()).isEqualTo(tmp);
    }

    @Test
    void 저장_테스트_실패_SIZE_초과() {
        String tmp = "000-0000-00000";
        Enterprise enterprise = Enterprise.builder()
                .registration_number(tmp)
                .name("잡플래닛")
                .establish_year("2004")
                .is_convention(true)
                .division(EnterpriseDivisionCode.LEADING_COMPANY)
                .representative_name("박상우")
                .headquarter(Headquarter.builder()
                        .postal_code("11111")
                        .address("headquarter_address")
                        .address_detail("headquarter_address_detail")
                        .build()
                )
                .branch(Branch.builder()
                        .branch_postal_code("11111")
                        .branch_address("branch_address")
                        .branch_address_detail("branch_address_detail")
                        .build()
                )
                .introduction("회사 소개")
                .employee_count(EnterpriseEmployeeCountCode.FIFTY_OR_MORE)
                .site("url")
                .turnover(128394)
                .director(Director.builder()
                        .director_email("director@gmail.com")
                        .director_name("박상우")
                        .director_telephone_number("01012345678")
                        .director_phone_number("01023462123")
                        .director_department("팀장")
                        .build()
                )
                .build();
        assertThrows(ConstraintViolationException.class, () -> enterpriseRepository.saveAndFlush(enterprise));
    }
}
