package com.jobits.dsm.benecia.domain.recruitment.presentation;

import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request.RecruitmentInfoListForTeacherRequest;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response.CurrentRecruitmentInfoListForStudentResponse;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response.RecruitmentInfoListForTeacherResponse;
import com.jobits.dsm.benecia.domain.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request.CreateRecruitmentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("recruitment")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @GetMapping
    public RecruitmentInfoListForTeacherResponse getRecruitmentInfoList(RecruitmentInfoListForTeacherRequest request) {
        return recruitmentService.getRecruitmentInfoList(request);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody CreateRecruitmentRequest request) {
        recruitmentService.createRecruitment(request);
    }

    @GetMapping("/current")
    public CurrentRecruitmentInfoListForStudentResponse getCurrentRecruitmentInfoList() {
        return recruitmentService.getCurrentRecruitmentInfoList();
    }
}
