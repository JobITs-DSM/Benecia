package com.jobits.dsm.benecia.domain.contract;

import com.jobits.dsm.benecia.domain.contract.domain.Contract;
import com.jobits.dsm.benecia.domain.contract.domain.ContractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class ContractRepositoryTest {

    @Autowired
    private ContractRepository contractRepository;

    @BeforeEach
    void cleanUp() {
        contractRepository.deleteAll();
    }

    @Test
    void 저장_테스트_성공() {
        Integer tmp = 1;
        Contract contract = Contract.builder()
                .id(tmp)
                .date_time(LocalDateTime.now())
                .build();

        assertThat(contractRepository.save(contract).getId()).isEqualTo(tmp);
    }
}
