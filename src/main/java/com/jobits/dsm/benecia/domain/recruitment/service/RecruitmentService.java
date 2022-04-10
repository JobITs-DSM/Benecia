package com.jobits.dsm.benecia.domain.recruitment.service;

import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.RecruitmentRepository;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response.RecruitmentInfoListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    public RecruitmentInfoListResponse getRecruitmentInfoList() {
        return RecruitmentInfoListResponse.builder()
                .recruitments(recruitmentRepository.getRecruitmentInfoList() // null 처리 추가 요망
                        .stream().map(recruitment -> RecruitmentInfoListResponse.RecruitmentInfo.builder()
                                .status(recruitment.getStatus())
                                .name(recruitment.getName())
                                .hiring(recruitment.getHiring().stream().map(HiringAreaCode::getValue).collect(Collectors.toList()))
                                .recruitCount(recruitment.getRecruitmentCount())
                                .applicantCount(recruitment.getApplicantCount())
                                .division(recruitment.getDivision())
                                .recruitBeginDate(recruitment.getRecruitBeginDate())
                                .recruitEndDate(recruitment.getRecruitEndDate())
                                .build()
                        ).collect(Collectors.toList()))
                .build();
    }
}
