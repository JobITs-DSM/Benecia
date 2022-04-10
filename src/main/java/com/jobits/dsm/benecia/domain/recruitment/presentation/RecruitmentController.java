package com.jobits.dsm.benecia.domain.recruitment.presentation;

import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request.RecruitmentInfoListRequest;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response.RecruitmentInfoListResponse;
import com.jobits.dsm.benecia.domain.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recruitment")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @GetMapping
    public RecruitmentInfoListResponse getRecruitmentInfoList(RecruitmentInfoListRequest request) {
        return recruitmentService.getRecruitmentInfoList(request);
    }
}
