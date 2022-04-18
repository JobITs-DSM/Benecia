package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.vo.AllRecruitmentInfoListForStudentVO;
import com.jobits.dsm.benecia.domain.recruitment.domain.vo.CurrentRecruitmentInfoListForStudentVO;
import com.jobits.dsm.benecia.domain.recruitment.domain.vo.RecruitmentInfoListForTeacherVO;
import com.jobits.dsm.benecia.domain.recruitment.type.SortCondition;

import java.time.LocalDate;
import java.util.List;

public interface RecruitmentRepositoryCustom {
    List<RecruitmentInfoListForTeacherVO> getRecruitmentInfoList(String receptionYear, String keyword, RecruitmentStatusCode recruitStatus, LocalDate beginDate, LocalDate endDate);
    List<CurrentRecruitmentInfoListForStudentVO> getCurrentRecruitmentInfoList(List<Integer> tagIds, List<HiringAreaCode> hiringAreaCodes, String keyword, Integer regionId, SortCondition sort);
    List<AllRecruitmentInfoListForStudentVO> queryAllRecruitmentInfoList(List<Integer> tagIds, List<HiringAreaCode> hiringAreaCodes, String keyword);
}
