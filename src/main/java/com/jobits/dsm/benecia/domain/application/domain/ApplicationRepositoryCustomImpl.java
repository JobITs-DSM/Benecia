package com.jobits.dsm.benecia.domain.application.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.jobits.dsm.benecia.domain.application.domain.QApplication.application;

@RequiredArgsConstructor
public class ApplicationRepositoryCustomImpl implements ApplicationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Long getApplicantCount(Integer hiringAreaId) {
        return queryFactory
                .selectFrom(application)
                .where(application.hiringAreaId.id.eq(hiringAreaId))
                .fetchCount();
    }
}
