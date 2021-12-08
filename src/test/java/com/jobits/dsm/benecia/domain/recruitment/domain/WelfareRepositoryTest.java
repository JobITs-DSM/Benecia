package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.recruitment.code.WelfareCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.welfare.Welfare;
import com.jobits.dsm.benecia.domain.recruitment.domain.welfare.WelfareRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaSystemException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class WelfareRepositoryTest {
    @Autowired
    private WelfareRepository welfareRepository;

    @BeforeEach
    void cleanUp() {
        welfareRepository.deleteAll();
    }

    @Test
    void 저장_성공_테스트() {
        Welfare welfare = Welfare.builder()
                .code(WelfareCode.DORMITORY)
                .build();

        assertThat(welfareRepository.save(welfare).getCode()).isEqualTo(WelfareCode.DORMITORY);
    }

    @Test
    void 저장_실패_테스트() {
        Welfare welfare = Welfare.builder()
                .code(null)
                .build();
        assertThrows(JpaSystemException.class, () -> welfareRepository.saveAndFlush(welfare));
    }
}
