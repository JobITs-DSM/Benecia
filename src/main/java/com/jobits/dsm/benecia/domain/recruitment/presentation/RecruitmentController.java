package com.jobits.dsm.benecia.domain.recruitment.presentation;

import com.jobits.dsm.benecia.domain.recruitment.domain.vo.RecruitmentDetailVO;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request.*;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response.*;
import com.jobits.dsm.benecia.domain.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("recruitment")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @GetMapping
    public RecruitmentInfoListForTeacherResponse getRecruitmentInfoList(@Valid RecruitmentInfoListForTeacherRequest request) {
        return recruitmentService.getRecruitmentInfoList(request);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody CreateRecruitmentRequest request) {
        recruitmentService.createRecruitment(request);
    }

    @GetMapping("/current")
    public CurrentRecruitmentInfoListForStudentResponse getCurrentRecruitmentInfoList(CurrentRecruitmentInfoListForStudentRequest request) {
        return recruitmentService.getCurrentRecruitmentInfoList(request);
    }

    @GetMapping("/{hiring-id}")
    public RecruitmentDetailResponse queryRecruitmentDetail(@PathVariable("hiring-id") Integer hiringId) {
        return recruitmentService.queryRecruitmentDetail(hiringId);
    }
  
    @GetMapping("/all")
    public AllRecruitmentInfoListForStudentResponse queryAllRecruitmentInfoList(AllRecruitmentInfoListForStudentRequest request) {
        return recruitmentService.queryAllRecruitmentInfoList(request);
    }

    @GetMapping("/similar")
    public SimilarRecruitmentInfoListForStudentResponse querySimilarRecruitmentInfoList(@Valid SimilarRecruitmentInfoListForStudentRequest request) {
        return recruitmentService.querySimilarRecruitmentInfoList(request);
    }
}
