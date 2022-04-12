package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.application.domain.Application;
import com.jobits.dsm.benecia.domain.contract.domain.Contract;
import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentFullTimePayCode;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentReportingTimeCode;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea.HiringArea;
import com.jobits.dsm.benecia.domain.recruitment.domain.programminglanguage.ProgrammingLanguage;
import com.jobits.dsm.benecia.domain.recruitment.domain.screeningprocess.ScreeningProcess;
import com.jobits.dsm.benecia.domain.recruitment.domain.tag.RecruitmentTag;
import com.jobits.dsm.benecia.domain.recruitment.domain.tag.Tag;
import com.jobits.dsm.benecia.domain.recruitment.domain.technology.Technology;
import com.jobits.dsm.benecia.domain.recruitment.domain.welfare.Welfare;
import com.jobits.dsm.benecia.domain.training.domain.Training;
import com.jobits.dsm.benecia.global.able.Savable;
import lombok.*;
import lombok.experimental.Delegate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Recruitment implements Savable {

    @EmbeddedId
    private RecruitmentId recruitmentId;

    @MapsId("registrationNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_number")
    private Enterprise enterprise;

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
    @Convert(converter = RecruitmentReportingTimeCode.RecruitmentReportingTimeCodeConverter.class)
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

    @Delegate
    @Embedded
    private Form form;

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private final List<ProgrammingLanguage> programmingLanguages = new ArrayList<>();

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private final List<HiringArea> hiringAreas = new ArrayList<>();

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private final List<Training> trainings = new ArrayList<>();

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private final List<Contract> contracts = new ArrayList<>();

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private final List<Welfare> welfare = new ArrayList<>();

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private final List<ScreeningProcess> screeningProcesses = new ArrayList<>();

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private final List<Technology> technologies = new ArrayList<>();

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private final List<RecruitmentTag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private final List<Application> applications = new ArrayList<>();

    @Override
    public String getDirectoryName() {
        return "recruitment";
    }

    @Override
    public String getId() {
        return recruitmentId.getRegistrationNumber();
    }
}