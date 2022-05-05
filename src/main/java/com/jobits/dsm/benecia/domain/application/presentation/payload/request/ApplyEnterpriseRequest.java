package com.jobits.dsm.benecia.domain.application.presentation.payload.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class ApplyEnterpriseRequest {
    @NotEmpty
    private String registrationNumber;
    @NotEmpty
    private String receptionYear;
    @NotNull
    private Integer hiringId;
    @NotNull
    private List<Integer> attachmentId;
}
