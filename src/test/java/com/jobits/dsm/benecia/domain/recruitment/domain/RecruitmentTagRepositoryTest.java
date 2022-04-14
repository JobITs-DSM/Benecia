package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseDivisionCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseEmployeeCountCode;
import com.jobits.dsm.benecia.domain.enterprise.domain.Address;
import com.jobits.dsm.benecia.domain.enterprise.domain.Director;
import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import com.jobits.dsm.benecia.domain.enterprise.domain.EnterpriseRepository;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentFullTimePayCode;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentReportingTimeCode;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.tag.*;
import com.jobits.dsm.benecia.infrastructure.querydsl.QuerydslConfig;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
public class RecruitmentTagRepositoryTest {
    @Autowired
    private RecruitmentTagRepository recruitmentTagRepository;
    @Autowired
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @BeforeEach
    void cleanUp() {
        recruitmentTagRepository.deleteAll();
        enterpriseRepository.deleteAll();
        tagRepository.deleteAll();
        recruitmentRepository.deleteAll();
    }

    @Test
    void 저장_테스트_성공() {
        Enterprise enterprise = getEnterprise();
        Recruitment recruitment = getRecruitment();
        Tag tag = tagRepository.save(Tag.builder().name("name").build());
        RecruitmentTag recruitmentTag = RecruitmentTag.builder()
                .tag(tag)
                .recruitmentTagId(new RecruitmentTagId(tag.getId(), new RecruitmentId("2021", "305-83-23458")))
                .recruitment(Recruitment.builder()
                        .recruitmentId(new RecruitmentId("2021", "305-83-23458"))
                        .status(RecruitmentStatusCode.RECRUITMENT_REQUEST)
                        .documentation(new Documentation("docu1", "docu2", "docu3"))
                        .printDateTime(null)
                        .form(new Form(null, null, null))
                        .fullTimePay(RecruitmentFullTimePayCode.LESS_2400)
                        .otherLanguage("Languages")
                        .otherSpecifics("Specifics")
                        .otherTechnology(null)
                        .recruitmentDate(RecruitmentDate.builder()
                                .recruitEndDate(LocalDate.of(2020, 9, 10))
                                .recruitBeginDate(LocalDate.of(2020, 9, 5))
                                .requestBeginDate(LocalDate.of(2020, 9, 1))
                                .build())
                        .preferential("preferential")
                        .report(1)
                        .build())
                .build();

        RecruitmentTag savedTag = recruitmentTagRepository.save(recruitmentTag);
        assertThat(savedTag.getRecruitment().getOtherLanguage()).isEqualTo(recruitment.getOtherLanguage());
        assertThat(savedTag.getTag().getId()).isEqualTo(tag.getId());
        assertThat(savedTag.getRecruitmentTagId().getTagId()).isEqualTo(1);
        assertThat(savedTag.getRecruitmentTagId().getRecruitmentId().getRegistrationNumber()).isEqualTo(enterprise.getRegistrationNumber());
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

        Enterprise enterprise = Enterprise.builder()
                .registrationNumber("305-83-23458")
                .name("잡플래닛")
                .establishYear("2021")
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
        return enterpriseRepository.save(enterprise);
    }

    private Recruitment getRecruitment() {
        RecruitmentId recruitmentId = new RecruitmentId("2021", "305-83-23458");
        return recruitmentRepository.save(Recruitment.builder()
                .recruitmentId(recruitmentId)
                .enterprise(getEnterprise())
                .status(RecruitmentStatusCode.RECRUITMENT_REQUEST)
                .documentation(new Documentation("docu1", "docu2", "docu3"))
                .printDateTime(null)
                .form(new Form(null, null, null))
                .fullTimePay(RecruitmentFullTimePayCode.LESS_2400)
                .otherLanguage("Languages")
                .otherSpecifics("Specifics")
                .recruitCount(1)
                .otherTechnology("technology")
                .trainingPay(11)
                .workPlace("workplace")
                .reportingTime(RecruitmentReportingTimeCode.BEFORE_TEN)
                .recruitmentDate(RecruitmentDate.builder()
                        .recruitEndDate(LocalDate.of(2020, 9, 10))
                        .recruitBeginDate(LocalDate.of(2020, 9, 5))
                        .requestBeginDate(LocalDate.of(2020, 9, 1))
                        .build())
                .preferential("preferential")
                .report(1)
                .build());
    }
}

