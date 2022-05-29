package com.jobits.dsm.benecia.domain.application.domain;

import com.jobits.dsm.benecia.domain.application.domain.vo.QueryApplicantListVO;

import java.util.List;

public interface ApplicationRepositoryCustom {
    List<QueryApplicantListVO> queryApplicantList(String registrationNumber, String receptionYear);
}
