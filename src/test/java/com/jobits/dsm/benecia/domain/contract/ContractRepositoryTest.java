package com.jobits.dsm.benecia.domain.contract;

import com.jobits.dsm.benecia.domain.contract.domain.Contract;
import com.jobits.dsm.benecia.domain.contract.domain.ContractRepository;
import com.jobits.dsm.benecia.infrastructure.querydsl.QuerydslConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
public class ContractRepositoryTest {

    @Autowired
    private ContractRepository contractRepository;

    @BeforeEach
    void cleanUp() {
        contractRepository.deleteAll();
    }

    @Test
    void 저장_테스트_성공() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Contract contract = Contract.builder()
                .dateTime(localDateTime)
                .build();

        assertThat(contractRepository.save(contract).getDateTime()).isEqualTo(localDateTime);
    }
}
