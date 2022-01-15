package com.jobits.dsm.benecia.domain.recruitment.service;

import com.jobits.dsm.benecia.domain.application.domain.ApplicationRepository;
import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import com.jobits.dsm.benecia.domain.recruitment.domain.RecruitmentRepository;
import com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea.HiringAreaRepository;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response.RecruitmentInfoListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final HiringAreaRepository hiringAreaRepository;
    private final ApplicationRepository applicationRepository;

    public RecruitmentInfoListResponse getRecruitmentInfoList() {
        return RecruitmentInfoListResponse.builder()
                .recruitments(recruitmentRepository.getRecruitmentInfoList()
                        .stream().map(recruitment -> RecruitmentInfoListResponse.RecruitmentInfo.builder()
                                .status(recruitment.getStatus())
                                .name(recruitment.getName())
                                .hiring(getHiringInfoList(recruitment.getReceptionYear(), recruitment.getRegistrationNumber()))
                                .division(recruitment.getDivision())
                                .recruitBeginDate(recruitment.getRecruitBeginDate())
                                .recruitEndDate(recruitment.getRecruitEndDate())
                                .build()
                        ).collect(Collectors.toList()))
                .build();
    }

    private List<RecruitmentInfoListResponse.HiringInfo> getHiringInfoList(String receptionYear, Enterprise registrationNumber) {
        return hiringAreaRepository.getHiringInfoList(receptionYear, registrationNumber)
                .stream().map(hiring -> RecruitmentInfoListResponse.HiringInfo.builder()
                        .hiringArea(hiring.getHiringArea())
                        .recruitCount(hiring.getRecruitCount())
                        .applicantCount(applicationRepository.getApplicantCount(hiring.getHiringId()))
                        .build()
                )
                .collect(Collectors.toList());

    }
}
