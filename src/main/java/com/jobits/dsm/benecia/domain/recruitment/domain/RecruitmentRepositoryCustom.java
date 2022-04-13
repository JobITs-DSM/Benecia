package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.vo.CurrentRecruitmentInfoListForStudentVO;
import com.jobits.dsm.benecia.domain.recruitment.domain.vo.RecruitmentInfoListForTeacherVO;

import java.time.LocalDate;
import java.util.List;

public interface RecruitmentRepositoryCustom {
    List<RecruitmentInfoListForTeacherVO> getRecruitmentInfoList(String receptionYear, String keyword, RecruitmentStatusCode recruitStatus, LocalDate beginDate, LocalDate endDate);
    List<CurrentRecruitmentInfoListForStudentVO> getCurrentRecruitmentInfoList();
}
