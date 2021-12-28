package com.jobits.dsm.benecia.domain.enterprise.presentation;

import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.RegisterEnterpriseRequest;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.EnterpriseListResponse;
import com.jobits.dsm.benecia.domain.enterprise.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
