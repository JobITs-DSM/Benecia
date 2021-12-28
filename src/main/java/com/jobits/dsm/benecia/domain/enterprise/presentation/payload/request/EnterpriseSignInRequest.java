package com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseSignInRequest {

    @NotBlank
    private String registrationNumber;

}
