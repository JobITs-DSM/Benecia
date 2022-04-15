package com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request;

import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import com.jobits.dsm.benecia.domain.recruitment.type.SortCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CurrentRecruitmentInfoListForStudentRequest {
    private List<Integer> tagIds;
    private List<HiringAreaCode> hiringCodes;
    private String keyword;
    private Integer regionId;
    private SortCondition sort;
}
