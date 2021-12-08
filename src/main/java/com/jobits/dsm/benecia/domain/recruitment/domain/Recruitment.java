package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.attatchment.domain.Attachment;
import com.jobits.dsm.benecia.domain.contract.domain.Contract;
import com.jobits.dsm.benecia.domain.recruitment.RecruitmentDate;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentFullTimePayCode;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentReportingTimeCode;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea.HiringArea;
import com.jobits.dsm.benecia.domain.recruitment.domain.programminglanguage.ProgrammingLanguage;
import com.jobits.dsm.benecia.domain.recruitment.domain.screeningprocess.ScreeningProcess;
import com.jobits.dsm.benecia.domain.recruitment.domain.technology.Technology;
import com.jobits.dsm.benecia.domain.recruitment.domain.welfare.Welfare;
import com.jobits.dsm.benecia.domain.training.domain.Training;
import lombok.*;
import lombok.experimental.Delegate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Recruitment {

    @EmbeddedId
    private RecruitmentId recruitmentId;

    @Convert(converter = RecruitmentStatusCode.RecruitmentStatusCodeConverter.class)
    private RecruitmentStatusCode status;

    @CreatedDate
    @NotNull
    private LocalDateTime printDateTime;

    @NotNull
    private String workPlace;

    @Column(columnDefinition = "TINYINT(1)")
    private Integer workingHour;

    @Column(columnDefinition = "CHAR(6)")
    @Convert(converter = RecruitmentReportingTimeCode.RecruitmentReportingTimeCodeCountCodeConverter.class)
    @NotNull
    private RecruitmentReportingTimeCode reportingTime;

    @Column(columnDefinition = "SMALLINT")
    @NotNull
    private Integer trainingPay;

    @Column(columnDefinition = "CHAR(6)")
    @Convert(converter = RecruitmentFullTimePayCode.RecruitmentFullTimePayCodeConverter.class)
    @NotNull
    private RecruitmentFullTimePayCode fullTimePay;

    @Delegate
    @Embedded
    private RecruitmentDate recruitmentDate;

    @NotNull
    private String otherLanguage;

    @NotNull
    private String otherTechnology;

    private String preferential;

    @Column(columnDefinition = "TINYINT")
    private Integer report;

    private String qualification;

    private String otherSpecifics;

    @Delegate
    @Embedded
    private Documentation documentation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form1")
    private Attachment form1;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form2")
    private Attachment form2;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form3")
    private Attachment form3;

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private List<ProgrammingLanguage> programmingLanguages;

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private List<HiringArea> hiringAreas;

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private List<Training> trainings;

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private List<Contract> contracts;

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private List<Welfare> welfare;

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private List<ScreeningProcess> screeningProcesses;

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private List<Technology> technologies;

}