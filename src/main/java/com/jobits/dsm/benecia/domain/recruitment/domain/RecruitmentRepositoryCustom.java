package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;

import java.time.LocalDate;
import java.util.List;

public interface RecruitmentRepositoryCustom {
    List<RecruitmentVO> getRecruitmentInfoList(String receptionYear, String keyword, RecruitmentStatusCode recruitStatus, LocalDate beginDate, LocalDate endDate);
}
