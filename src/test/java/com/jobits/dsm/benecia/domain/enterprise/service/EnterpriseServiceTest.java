package com.jobits.dsm.benecia.domain.enterprise.service;

import com.jobits.dsm.benecia.domain.enterprise.code.BusinessAreaCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseDivisionCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseEmployeeCountCode;
import com.jobits.dsm.benecia.domain.enterprise.domain.Address;
import com.jobits.dsm.benecia.domain.enterprise.domain.Director;
import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import com.jobits.dsm.benecia.domain.enterprise.domain.EnterpriseRepository;
import com.jobits.dsm.benecia.domain.enterprise.domain.businessarea.BusinessArea;
import com.jobits.dsm.benecia.domain.enterprise.domain.businessarea.BusinessAreaRepository;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.ModifyEnterpriseInfoRequest;
import com.jobits.dsm.benecia.infrastructure.querydsl.QuerydslConfig;
import com.jobits.dsm.benecia.infrastructure.redis.EmbeddedRedisConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(QuerydslConfig.class)
public class EnterpriseServiceTest {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private BusinessAreaRepository businessAreaRepository;

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

    @Test
    void 사업분야_일치() {
        Enterprise enterprise = enterpriseRepository.save(getEnterprise());

        List<BusinessAreaCode> businessAreaCodes = new ArrayList<>();
        businessAreaCodes.add(BusinessAreaCode.SECURITY);
        businessAreaCodes.add(BusinessAreaCode.GAME);

        businessAreaCodes
                .forEach(businessAreaCode -> {
                    enterprise.getBusinessAreas().add(businessAreaRepository.save(BusinessArea.builder()
                            .code(businessAreaCode)
                            .enterprise(enterprise)
                            .build()));
                });

        enterpriseService.modifyEnterpriseInfo(enterprise.getRegistrationNumber(), getModifiedEnterprise());

        assertThat(enterpriseRepository.getBusinessAreas(enterprise.getRegistrationNumber())).isEqualTo(getModifiedEnterprise().getBusinessAreas());
    }

    private ModifyEnterpriseInfoRequest getModifiedEnterprise() {
        List<BusinessAreaCode> codes = new ArrayList<>();
        codes.add(BusinessAreaCode.SOLUTION);
        codes.add(BusinessAreaCode.EDU_TECH);

        return new ModifyEnterpriseInfoRequest(
                "박상우",
                "2022",
                "박상우",
                "31233",
                "서울특별시 강남구",
                "2층",
                "31244",
                "서울특",
                "2층",
                "FIVE_PEOPLE_OR_LESS",
                EnterpriseEmployeeCountCode.FIVE_PEOPLE_OR_LESS,
                "https://www.musinsa.com",
                1000000,
                "wkejf@gmail.com",
                "정지우",
                "070-7763-74563",
                "010-8442-7456",
                "인사부 담당",
                codes
        );
    }

    private Enterprise getEnterprise() {
        Address address = Address.builder()
                .postalCode("11111")
                .address("headquarter_address")
                .address("headquarter_address_detail")
                .build();

        Address branchAddress = Address.builder()
                .postalCode("11111")
                .address("branch_address")
                .addressDetail("branch_address_detail")
                .build();

        Director director = Director.builder()
                .email("director@gmail.com")
                .name("박상우")
                .telephoneNumber("01012345678")
                .phoneNumber("01023462123")
                .department("팀장")
                .build();

        return Enterprise.builder()
                .registrationNumber("000-00-00000")
                .name("잡플래닛")
                .establishYear("2004")
                .isConvention(true)
                .division(EnterpriseDivisionCode.LEADING_COMPANY)
                .representativeName("박상우")
                .address(address)
                .branchAddress(branchAddress)
                .introduction("회사 소개")
                .employeeCount(EnterpriseEmployeeCountCode.FIFTY_OR_MORE)
                .site("url")
                .turnover(128394)
                .director(director)
                .build();
    }

}
