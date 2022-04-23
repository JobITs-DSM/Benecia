package com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request;

import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
public class SimilarRecruitmentInfoListForStudentRequest {
    @NotNull
    private List<HiringAreaCode> hiringCodes;
    @NotNull
    private Integer region;
}
