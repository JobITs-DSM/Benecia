package com.jobits.dsm.benecia.domain.recruitment;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Embeddable
public class RecruitmentDate {

    @NotNull
    private LocalDate requestBeginDate;

    @NotNull
    private LocalDate recruitBeginDate;

    @NotNull
    private LocalDate recruitEndDate;

}
