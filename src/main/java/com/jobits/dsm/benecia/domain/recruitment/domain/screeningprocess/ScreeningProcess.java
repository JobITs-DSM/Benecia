package com.jobits.dsm.benecia.domain.recruitment.domain.screeningprocess;

import com.jobits.dsm.benecia.domain.recruitment.code.ScreeningProcessCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.Recruitment;
import com.jobits.dsm.benecia.domain.recruitment.domain.RecruitmentId;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScreeningProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Convert(converter = ScreeningProcessCode.ScreeningProcessCodeConverter.class)
    private ScreeningProcessCode code;

    @NotNull
    @Column(columnDefinition = "TINYINT")
    private Integer gradations;

    @ManyToOne
    @JoinColumn(name = "reception_year")
    @JoinColumn(name = "registration_number")
    private Recruitment recruitment;
}
