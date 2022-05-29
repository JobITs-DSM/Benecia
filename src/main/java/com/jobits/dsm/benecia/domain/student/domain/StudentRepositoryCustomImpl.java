package com.jobits.dsm.benecia.domain.student.domain;

import com.jobits.dsm.benecia.domain.application.domain.QApplication;
import com.jobits.dsm.benecia.domain.attachment.domain.QAttachment;
import com.jobits.dsm.benecia.domain.enterprise.domain.QEnterprise;
import com.jobits.dsm.benecia.domain.recruitment.domain.QRecruitment;
import com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea.QHiringArea;
import com.jobits.dsm.benecia.domain.student.domain.vo.QSearchStudentListVO;
import com.jobits.dsm.benecia.domain.student.domain.vo.QStudentCurrentStatusVO;
import com.jobits.dsm.benecia.domain.student.domain.vo.SearchStudentListVO;
import com.jobits.dsm.benecia.domain.student.domain.vo.StudentCurrentStatusVO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.jobits.dsm.benecia.domain.application.domain.QApplication.application;
import static com.jobits.dsm.benecia.domain.attachment.domain.QAttachment.attachment;
import static com.jobits.dsm.benecia.domain.enterprise.domain.QEnterprise.enterprise;
import static com.jobits.dsm.benecia.domain.recruitment.domain.QRecruitment.recruitment;
import static com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea.QHiringArea.hiringArea;
import static com.jobits.dsm.benecia.domain.student.domain.QStudent.student;

@RequiredArgsConstructor
public class StudentRepositoryCustomImpl implements StudentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public StudentCurrentStatusVO queryStudentCurrentStatus(String serialNumber) {
        return queryFactory
                .select(new QStudentCurrentStatusVO(
                        student.name,
                        student.profileImage.fileName,
                        application.status,
                        enterprise.logo.fileName,
                        hiringArea.code
                ))
                .from(student)
                .join(student.applications, application)
                .join(student.profileImage, attachment)
                .join(application.hiringAreaId, hiringArea)
                .join(hiringArea.recruitment, recruitment)
                .join(recruitment.enterprise, enterprise)
                .join(enterprise.logo, attachment)
                .where(student.serialNumber.eq(serialNumber))
                .fetchFirst();
    }

    @Override
    public List<SearchStudentListVO> searchStudentList(String name) {
        return queryFactory
                .select(new QSearchStudentListVO(
                        student.name,
                        student.studentNumber
                ))
                .from(student)
                .where(isNameEq(name))
                .fetch();
    }

    private BooleanExpression isNameEq(String name) {
        return name == null || name.isEmpty() ? null : student.name.contains(name);
    }
}
