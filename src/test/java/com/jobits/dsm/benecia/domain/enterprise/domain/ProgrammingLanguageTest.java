package com.jobits.dsm.benecia.domain.enterprise.domain;

import com.jobits.dsm.benecia.domain.recruitment.code.ProgrammingLanguageCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.programminglanguage.ProgrammingLanguage;
import com.jobits.dsm.benecia.domain.recruitment.domain.programminglanguage.ProgrammingLanguageRepository;
import com.jobits.dsm.benecia.infrastructure.querydsl.QuerydslConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
public class ProgrammingLanguageTest {

    @Autowired
    private ProgrammingLanguageRepository programmingLanguageRepository;

    @Test
    void 중첩클래스_컨버터_작동확인() {
        ProgrammingLanguage programmingLanguage = programmingLanguageRepository.save(ProgrammingLanguage.builder()
                .code(ProgrammingLanguageCode.GO)
                .build());

        assertThat(programmingLanguage.getCode()).isEqualTo(ProgrammingLanguageCode.GO);
    }
}
