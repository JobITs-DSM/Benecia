package com.jobits.dsm.benecia.domain.recruitment.presentation;

import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request.CreateRecruitmentRequest;
import com.jobits.dsm.benecia.domain.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("recruitment")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody CreateRecruitmentRequest request) {
        recruitmentService.createRecruitment(request);
    }
}
