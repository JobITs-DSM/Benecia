package com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request;

import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AllRecruitmentInfoListForStudentRequest {
    private List<Integer> tagIds;
    private List<HiringAreaCode> hiringCodes;
    private String keyword;
}
