package com.jobits.dsm.benecia.domain.enterprise.service;

import com.jobits.dsm.benecia.domain.enterprise.domain.EnterpriseRepository;
import com.jobits.dsm.benecia.infrastructure.querydsl.QuerydslConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(QuerydslConfig.class)
public class EnterpriseServiceTest {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @BeforeEach
    void cleanUp() {
        enterpriseRepository.deleteAll();
    }

    @Test
    void 사업자번호_정규표현식() {
        String pattern = "^\\d{3}-\\d{2}-\\d{5}";

        String correctRegistrationNumber = "111-11-11111";
        String wrongRegistrationNumber = "1111-111111";

        assertThat(Pattern.matches(pattern, correctRegistrationNumber)).isTrue();
        assertThat(Pattern.matches(pattern, wrongRegistrationNumber)).isFalse();
    }
}
