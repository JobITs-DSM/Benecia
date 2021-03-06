package com.jobits.dsm.benecia.domain.application.presentation;

import com.jobits.dsm.benecia.domain.application.presentation.payload.request.ApplyEnterpriseRequest;
import com.jobits.dsm.benecia.domain.application.service.ApplicationService;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response.QueryApplicantListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void applyEnterprise(@RequestBody @Valid ApplyEnterpriseRequest request) {
        applicationService.applyEnterprise(request);
    }

    @GetMapping("/{registration-number}/{reception-year}")
    public QueryApplicantListResponse queryApplicantList(@PathVariable("registration-number") String registrationNumber,
                                                         @PathVariable("reception-year") String receptionYear) {
        return applicationService.queryApplicantList(registrationNumber, receptionYear);
    }
}
