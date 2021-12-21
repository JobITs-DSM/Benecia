package com.jobits.dsm.benecia.domain.enterprise.service;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class EnterpriseServiceTest {

    @Test
    void 사업자번호_정규표현식() {
        String pattern = "^\\d{3}-\\d{2}-\\d{5}";

        String correctRegistrationNumber = "111-11-11111";
        String wrongRegistrationNumber = "1111-111111";

        assertThat(Pattern.matches(pattern, correctRegistrationNumber)).isTrue();
        assertThat(Pattern.matches(pattern, wrongRegistrationNumber)).isFalse();
    }
}
