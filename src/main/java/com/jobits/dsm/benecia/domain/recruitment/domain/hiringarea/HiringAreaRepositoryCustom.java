package com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea;

import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;

import java.util.List;

public interface HiringAreaRepositoryCustom {
    List<HiringAreaVO> getHiringInfoList(String receptionYear, Enterprise registrationNumber);
}
