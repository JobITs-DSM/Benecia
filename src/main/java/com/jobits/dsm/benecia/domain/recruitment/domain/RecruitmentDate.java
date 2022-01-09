package com.jobits.dsm.benecia.domain.recruitment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class RecruitmentDate {

    @Column(nullable = false)
    private LocalDate requestBeginDate;

    @Column(nullable = false)
    private LocalDate recruitBeginDate;

    @Column(nullable = false)
    private LocalDate recruitEndDate;

}
