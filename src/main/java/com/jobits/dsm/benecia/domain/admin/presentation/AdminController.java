package com.jobits.dsm.benecia.domain.admin.presentation;

import com.jobits.dsm.benecia.domain.admin.presentation.payload.request.AdminLoginRequest;
import com.jobits.dsm.benecia.domain.admin.presentation.payload.response.AdminTokenResponse;
import com.jobits.dsm.benecia.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.CREATED)
    public AdminTokenResponse signIn(@RequestBody @Valid AdminLoginRequest request) {
        return adminService.signIn(request);
    }
}
