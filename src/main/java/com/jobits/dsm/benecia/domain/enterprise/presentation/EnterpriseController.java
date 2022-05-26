package com.jobits.dsm.benecia.domain.enterprise.presentation;

import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.EnterpriseSignInRequest;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.ModifyEnterpriseInfoRequest;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.RegisterEnterpriseRequest;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.EnterpriseInfoListResponse;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.EnterpriseInfoResponse;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.EnterpriseTokenResponse;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.LeadingEnterpriseListResponse;
import com.jobits.dsm.benecia.domain.enterprise.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody @Valid RegisterEnterpriseRequest request) {
        enterpriseService.registerEnterprise(request);
    }

    @GetMapping
    EnterpriseInfoListResponse getEnterpriseInfoList() {
        return enterpriseService.getEnterpriseInfoList();
    }
  
    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.CREATED)
    EnterpriseTokenResponse signIn(@RequestBody @Valid EnterpriseSignInRequest request) {
        return enterpriseService.enterpriseSignIn(request);
    }

    @GetMapping("/{registration_number}")
    EnterpriseInfoResponse getEnterpriseInfo(@PathVariable("registration_number") String request) {
        return enterpriseService.getEnterpriseInfo(request);
    }
  
    @PutMapping("/{registration_number}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void modifyEnterpriseInfo(@PathVariable("registration_number") String registrationNumber, @RequestBody @Valid ModifyEnterpriseInfoRequest request) {
        enterpriseService.modifyEnterpriseInfo(registrationNumber, request);
    }

    @GetMapping("/leading")
    List<LeadingEnterpriseListResponse> leadingEnterprise() {
        return enterpriseService.getLeadingEnterprise();
    }
}
