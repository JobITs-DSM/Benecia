package com.jobits.dsm.benecia.domain.student.domain.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class SearchStudentListVO {
    private final String name;
    private final String number;

    @QueryProjection
    public SearchStudentListVO(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
