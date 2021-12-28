package com.jobits.dsm.benecia.domain.enterprise.presentation;

import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.EnterpriseSignInRequest;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.RegisterEnterpriseRequest;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.EnterpriseListResponse;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.EnterpriseTokenResponse;
import com.jobits.dsm.benecia.domain.enterprise.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@ModelAttribute RegisterEnterpriseRequest request) {
        enterpriseService.registerEnterprise(request);
    }

    @GetMapping
    EnterpriseListResponse getEnterpriseList() {
        return enterpriseService.getEnterpriseList();
    }
  
    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.CREATED)
    EnterpriseTokenResponse signIn(@RequestBody @Valid EnterpriseSignInRequest request) {
        return enterpriseService.enterpriseSignIn(request);
    }
}
