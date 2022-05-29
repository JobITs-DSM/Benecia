package com.jobits.dsm.benecia.domain.application.domain;

import com.jobits.dsm.benecia.domain.application.domain.vo.QQueryApplicantListVO;
import com.jobits.dsm.benecia.domain.application.domain.vo.QueryApplicantListVO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.jobits.dsm.benecia.domain.application.domain.QApplication.application;
import static com.jobits.dsm.benecia.domain.application.domain.QApplicationAttachment.applicationAttachment;
import static com.jobits.dsm.benecia.domain.attachment.domain.QAttachment.attachment;
import static com.jobits.dsm.benecia.domain.recruitment.domain.QRecruitment.recruitment;
import static com.jobits.dsm.benecia.domain.student.domain.QStudent.student;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@RequiredArgsConstructor
public class ApplicationRepositoryCustomImpl implements ApplicationRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<QueryApplicantListVO> queryApplicantList(String registrationNumber, String receptionYear) {
        return queryFactory
                .selectFrom(application)
                .join(application.applicationAttachments, applicationAttachment)
                .join(application.recruitment, recruitment)
                .join(application.studentSerialNumber, student)
                .join(applicationAttachment.attachment, attachment)
                .on(
                        recruitment.recruitmentId.registrationNumber.eq(registrationNumber),
                        recruitment.recruitmentId.receptionYear.eq(receptionYear)
                )
                .transform(groupBy(application.id)
                        .list(new QQueryApplicantListVO(
                                student.studentNumber,
                                student.name,
                                application.dateTime,
                                list(applicationAttachment.attachment)
                        ))
                );
    }
}
