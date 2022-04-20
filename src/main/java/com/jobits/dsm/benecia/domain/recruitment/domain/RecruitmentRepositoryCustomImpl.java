package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.attachment.domain.QAttachment;
import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.programminglanguage.QProgrammingLanguage;
import com.jobits.dsm.benecia.domain.recruitment.domain.screeningprocess.QScreeningProcess;
import com.jobits.dsm.benecia.domain.recruitment.domain.tag.QRecruitmentTag;
import com.jobits.dsm.benecia.domain.recruitment.domain.tag.QTag;
import com.jobits.dsm.benecia.domain.recruitment.domain.technology.QTechnology;
import com.jobits.dsm.benecia.domain.recruitment.domain.vo.*;
import com.jobits.dsm.benecia.domain.recruitment.domain.welfare.QWelfare;
import com.jobits.dsm.benecia.domain.recruitment.type.SortCondition;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.jobits.dsm.benecia.domain.application.domain.QApplication.*;
import static com.jobits.dsm.benecia.domain.attachment.domain.QAttachment.attachment;
import static com.jobits.dsm.benecia.domain.enterprise.domain.QEnterprise.*;
import static com.jobits.dsm.benecia.domain.recruitment.domain.QRecruitment.*;
import static com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea.QHiringArea.*;
import static com.jobits.dsm.benecia.domain.recruitment.domain.programminglanguage.QProgrammingLanguage.programmingLanguage;
import static com.jobits.dsm.benecia.domain.recruitment.domain.screeningprocess.QScreeningProcess.screeningProcess;
import static com.jobits.dsm.benecia.domain.recruitment.domain.tag.QRecruitmentTag.recruitmentTag;
import static com.jobits.dsm.benecia.domain.recruitment.domain.tag.QTag.tag;
import static com.jobits.dsm.benecia.domain.recruitment.domain.technology.QTechnology.technology;
import static com.jobits.dsm.benecia.domain.recruitment.domain.welfare.QWelfare.welfare;
import static com.querydsl.core.group.GroupBy.*;
import static com.jobits.dsm.benecia.domain.recruitment.type.SortCondition.LATEST;
import static com.jobits.dsm.benecia.domain.recruitment.type.SortCondition.POPULAR;
import static com.querydsl.jpa.JPAExpressions.select;

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
                                list(hiringArea.code),
                                recruitment.recruitCount,
                                Expressions.asNumber(
                                        select(application.count())
                                                .from(application)
                                                .where(application.recruitment.recruitmentId.registrationNumber.eq(recruitment.recruitmentId.registrationNumber))
                                ).longValue(),
                                enterprise.division,
                                recruitment.recruitmentDate.recruitBeginDate,
                                recruitment.recruitmentDate.recruitEndDate
                        ))
                );
    }

    @Override
    public List<CurrentRecruitmentInfoListForStudentVO> getCurrentRecruitmentInfoList(List<Integer> tagIds, List<HiringAreaCode> hiringAreaCodes, String keyword, Integer regionId, SortCondition sort) {
        return queryFactory
                .selectFrom(recruitment).distinct()
                .join(recruitment.hiringAreas, hiringArea)
                .join(recruitment.enterprise, enterprise)
                .join(recruitment.tags, recruitmentTag)
                .join(recruitmentTag.tag, tag)
                .join(enterprise.logo, attachment)
                .join(enterprise.foreground, attachment)
                .leftJoin(recruitment.applications, application)
                .where(
                        recruitment.recruitmentId.receptionYear.eq(Integer.toString(LocalDate.now().getYear())),
                        keywordEq(keyword),
                        hiringAreaEq(hiringAreaCodes),
                        tagsEq(tagIds),
                        regionEq(regionId)
                )
                .orderBy(buildSortCondition(sort))
                .groupBy(recruitment.recruitmentId.registrationNumber, recruitment.recruitmentId.receptionYear, hiringArea.code, recruitment.recruitCount, tag.name, attachment.id)
                .transform(groupBy(recruitment.recruitmentId.registrationNumber, recruitment.recruitmentId.receptionYear, recruitment.recruitCount, hiringArea.code)
                        .list(new QCurrentRecruitmentInfoListForStudentVO(
                                application.count(),
                                hiringArea.id,
                                hiringArea.code,
                                recruitment.recruitCount,
                                enterprise.name,
                                recruitment.workPlace,
                                list(tag.name),
                                enterprise.logo.fileName,
                                enterprise.foreground.fileName
                        ))
                );
    }

    @Override
    public RecruitmentDetailVO queryRecruitmentDetail(Integer hiringId) {
        return queryFactory
                .select(new QRecruitmentDetailVO(
                        recruitment.recruitmentId.registrationNumber,
                        recruitment.recruitmentId.receptionYear,
                        enterprise.foreground.fileName,
                        enterprise.logo.fileName,
                        enterprise.introduction,
                        enterprise.name,
                        recruitment.workPlace,
                        hiringArea.code,
                        hiringArea.task,
                        recruitment.recruitCount,
                        recruitment.workingHour,
                        recruitment.reportingTime,
                        recruitment.trainingPay,
                        recruitment.fullTimePay,
                        recruitment.recruitmentDate.recruitBeginDate,
                        recruitment.recruitmentDate.recruitEndDate,
                        recruitment.otherLanguage,
                        recruitment.otherTechnology,
                        recruitment.preferential,
                        recruitment.report,
                        recruitment.qualification,
                        recruitment.otherSpecifics,
                        recruitment.documentation.documentation1,
                        recruitment.documentation.documentation2,
                        recruitment.documentation.documentation3,
                        recruitment.form.form1.id,
                        recruitment.form.form2.id,
                        recruitment.form.form3.id))
                .from(recruitment)
                .join(recruitment.hiringAreas, hiringArea)
                .join(recruitment.enterprise, enterprise)
                .join(enterprise.logo, attachment)
                .join(enterprise.foreground, attachment)
                .join(recruitment.form.form1, attachment)
                .join(recruitment.form.form2, attachment)
                .join(recruitment.form.form3, attachment)
                .where(hiringArea.id.eq(hiringId))
                .fetchFirst();
    } 

    public List<AllRecruitmentInfoListForStudentVO> queryAllRecruitmentInfoList(List<Integer> tagIds, List<HiringAreaCode> hiringAreaCodes, String keyword) {
        return queryFactory
                .selectFrom(recruitment)
                .join(recruitment.hiringAreas, hiringArea)
                .join(recruitment.enterprise, enterprise)
                .join(recruitment.tags, recruitmentTag)
                .join(recruitmentTag.tag, tag).distinct()
                .join(enterprise.logo, attachment)
                .leftJoin(recruitment.applications, application)
                .where(
                        keywordEq(keyword),
                        hiringAreaEq(hiringAreaCodes),
                        tagsEq(tagIds)
                )
                .orderBy(buildSortCondition(POPULAR))
                .groupBy(recruitment.recruitmentId.registrationNumber, recruitment.recruitmentId.receptionYear, hiringArea.code, recruitment.recruitCount, tag.name, attachment.id, recruitment.recruitmentDate.recruitEndDate, recruitment.fullTimePay)
                .transform(groupBy(recruitment.recruitmentId.registrationNumber, recruitment.recruitmentId.receptionYear, recruitment.recruitCount, recruitment.recruitmentDate.recruitEndDate, recruitment.fullTimePay, hiringArea.code)
                        .list(new QAllRecruitmentInfoListForStudentVO(
                                application.count(),
                                hiringArea.id,
                                hiringArea.code,
                                recruitment.recruitCount,
                                enterprise.name,
                                enterprise.introduction,
                                list(tag.name),
                                enterprise.logo.fileName,
                                recruitment.recruitmentDate.recruitEndDate
                        ))
                );
    }

    private BooleanExpression keywordEq(String keyword) {
        return keyword == null || keyword.isEmpty() ? null : recruitment.enterprise.name.contains(keyword);
    }

    private OrderSpecifier[] buildSortCondition(SortCondition sort) {
        return switch (sort) {
            case LATEST -> new OrderSpecifier[]{
                    recruitment.printDateTime.desc(), recruitment.fullTimePay.desc()
            };
            case PERIOD -> new OrderSpecifier[]{
                    recruitment.recruitmentDate.recruitEndDate.asc(), recruitment.fullTimePay.desc()
            };
            case POPULAR -> new OrderSpecifier[]{
                    application.count().desc(), recruitment.fullTimePay.desc()
            };
        };
    }

    private BooleanExpression tagsEq(List<Integer> tagIds) {
        return tagIds != null ? tag.id.in(tagIds) : null;
    }

    private BooleanExpression hiringAreaEq(List<HiringAreaCode> hiringAreaCodes) {
        return hiringAreaCodes != null ? hiringArea.code.in(hiringAreaCodes) : null;
    }

    private BooleanExpression regionEq(Integer regionId) {
        return regionId != null ? enterprise.region.id.eq(regionId) : null;
    }
}
