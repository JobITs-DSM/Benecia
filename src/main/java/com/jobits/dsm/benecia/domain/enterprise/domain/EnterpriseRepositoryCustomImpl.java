package com.jobits.dsm.benecia.domain.enterprise.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.jobits.dsm.benecia.domain.contract.domain.QContract.contract;
import static com.jobits.dsm.benecia.domain.enterprise.domain.QEnterprise.enterprise;
import static com.jobits.dsm.benecia.domain.recruitment.domain.QRecruitment.recruitment;
import static com.jobits.dsm.benecia.domain.review.domain.QReview.review;

@RequiredArgsConstructor
public class EnterpriseRepositoryCustomImpl implements EnterpriseRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Long getContractStudentCount(String registrationNumber) {
        return queryFactory
                .selectFrom(contract)
                .join(contract.recruitment, recruitment)
                .where(recruitment.recruitmentId.registrationNumber.eq(registrationNumber))
                .fetchCount();
    }

    @Override
    public Long getReviewCount(String registrationNumber) {
        return queryFactory
                .selectFrom(review)
                .join(review.enterprise, enterprise)
                .where(enterprise.registrationNumber.eq(registrationNumber))
                .fetchCount();
    }
}
