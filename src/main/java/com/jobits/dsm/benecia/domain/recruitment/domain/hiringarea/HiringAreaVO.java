package com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea;

import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class HiringAreaVO {
    private final Integer hiringId;
    private final HiringAreaCode hiringArea;
    private final Integer recruitCount;

    @QueryProjection
    public HiringAreaVO(Integer hiringId, HiringAreaCode hiringArea, Integer recruitCount) {
        this.hiringId = hiringId;
        this.hiringArea = hiringArea;
        this.recruitCount = recruitCount;
    }
}
