package com.jobits.dsm.benecia.domain.enterprise.domain;

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
        String tmp = "111111111111";
        Enterprise enterprise = Enterprise.builder()
                .registration_number(tmp)
                .build();
        assertThat(enterpriseRepository.save(enterprise).getRegistration_number()).isEqualTo(tmp);
    }

    @Test
    void 저장_테스트_실패_SIZE_초과() {
        String tmp = "123456789101112";
        Enterprise enterprise = Enterprise.builder()
                .registration_number(tmp)
                .build();
        assertThrows(ConstraintViolationException.class, () -> enterpriseRepository.saveAndFlush(enterprise));
    }
}
