package com.jobits.dsm.benecia.domain.admin.presentation.payload.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminLoginRequest {

    @NotBlank
    private String id;

    @NotBlank
    private String password;
}
