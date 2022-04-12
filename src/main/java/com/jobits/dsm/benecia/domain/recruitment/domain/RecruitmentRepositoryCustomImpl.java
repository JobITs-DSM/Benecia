package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.jobits.dsm.benecia.domain.application.domain.QApplication.*;
import static com.jobits.dsm.benecia.domain.enterprise.domain.QEnterprise.*;
import static com.jobits.dsm.benecia.domain.recruitment.domain.QRecruitment.*;
import static com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea.QHiringArea.*;
import static com.querydsl.core.group.GroupBy.groupBy;

@RequiredArgsConstructor
public class RecruitmentRepositoryCustomImpl implements RecruitmentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<RecruitmentInfoListForTeacherVO> getRecruitmentInfoList(String receptionYear, String keyword, RecruitmentStatusCode recruitStatus, LocalDate beginDate, LocalDate endDate) {
        return queryFactory
                .selectFrom(recruitment)
                .join(recruitment.hiringAreas, hiringArea)
                .leftJoin(recruitment.applications, application)
                .join(recruitment.enterprise, enterprise)
                .where(
                        recruitment.recruitmentId.receptionYear.eq(receptionYear),
                        keywordEq(keyword),
                        recruitment.status.eq(recruitStatus),
                        recruitment.recruitmentDate.recruitBeginDate.goe(beginDate),
                        recruitment.recruitmentDate.recruitEndDate.loe(endDate)
                )
                .orderBy(recruitment.recruitmentDate.recruitBeginDate.desc())
                .transform(groupBy(recruitment.enterprise.registrationNumber, recruitment.recruitmentId.receptionYear, recruitment.recruitmentDate.recruitBeginDate)
                        .list(new QRecruitmentInfoListForTeacherVO(
                                recruitment.status,
                                recruitment.enterprise.name,
                                GroupBy.list(hiringArea.code),
                                Expressions.asNumber(
                                        JPAExpressions.select(hiringArea.count.sum())
                                                .from(hiringArea)
                                                .where(hiringArea.recruitment.recruitmentId.registrationNumber.eq(recruitment.recruitmentId.registrationNumber))
                                ).intValue(),
                                Expressions.asNumber(
                                        JPAExpressions.select(application.count())
                                                .from(application)
                                                .where(application.recruitment.recruitmentId.registrationNumber.eq(recruitment.recruitmentId.registrationNumber))
                                ).longValue(),
                                enterprise.division,
                                recruitment.recruitmentDate.recruitBeginDate,
                                recruitment.recruitmentDate.recruitEndDate
                        ))
                );
    }

    private BooleanExpression keywordEq(String keyword) {
        return keyword == null || keyword.isEmpty() ? null : recruitment.enterprise.name.eq(keyword);
    }
}
