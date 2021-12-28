package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.recruitment.code.TechnologyCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.technology.Technology;
import com.jobits.dsm.benecia.domain.recruitment.domain.technology.TechnologyRepository;
import com.jobits.dsm.benecia.infrastructure.querydsl.QuerydslConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaSystemException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import(QuerydslConfig.class)
public class TechnologyRepositoryTest {
    @Autowired
    private TechnologyRepository technologyRepository;

    @BeforeEach
    void cleanUp() {
        technologyRepository.deleteAll();
    }

    @Test
    void 저장_성공_테스트() {
        Technology technology = Technology.builder()
                .code(TechnologyCode.ANGULAR_JS)
                .build();
        assertThat(technologyRepository.save(technology).getCode()).isEqualTo(TechnologyCode.ANGULAR_JS);
    }

    @Test
    void 저장_실패_테스트() {
        Technology technology = Technology.builder()
                .code(null)
                .build();
        assertThrows(JpaSystemException.class, () -> technologyRepository.saveAndFlush(technology));
    }
}
