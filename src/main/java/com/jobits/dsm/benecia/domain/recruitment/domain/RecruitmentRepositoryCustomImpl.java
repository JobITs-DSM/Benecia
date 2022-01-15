package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.jobits.dsm.benecia.domain.enterprise.domain.QEnterprise.enterprise;
import static com.jobits.dsm.benecia.domain.recruitment.domain.QRecruitment.recruitment;

@RequiredArgsConstructor
public class RecruitmentRepositoryCustomImpl implements RecruitmentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<RecruitmentVO> getRecruitmentInfoList() {
        return queryFactory
                .select(new QRecruitmentVO(
                                recruitment.recruitmentId.receptionYear,
                                recruitment.registrationNumber,
                                recruitment.status,
                                enterprise.name,
                                enterprise.division,
                                recruitment.recruitmentDate.recruitBeginDate,
                                recruitment.recruitmentDate.recruitEndDate
                        )
                )
                .from(recruitment)
                .join(recruitment.registrationNumber, enterprise)
                .orderBy(recruitment.recruitmentDate.requestBeginDate.desc())
                .fetch();
    }
}
