package com.jobits.dsm.benecia.domain.recruitment.service;

import com.jobits.dsm.benecia.domain.attachment.domain.Attachment;
import com.jobits.dsm.benecia.domain.attachment.facade.AttachmentFacade;
import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.*;
import com.jobits.dsm.benecia.domain.recruitment.domain.programminglanguage.ProgrammingLanguageRepository;
import com.jobits.dsm.benecia.domain.recruitment.facade.RecruitmentFacade;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request.CreateRecruitmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    private final RecruitmentFacade recruitmentFacade;
    private final AttachmentFacade attachmentFacade;

    @Transactional
    public void createRecruitment(CreateRecruitmentRequest request) {
        String registrationNumber = SecurityContextHolder.getContext().getAuthentication().getName();

        RecruitmentId recruitmentId = new RecruitmentId(request.getReceptionYear(), registrationNumber);

        Recruitment recruitment = recruitmentRepository.save(Recruitment.builder()
                .recruitmentId(recruitmentId)
                .status(RecruitmentStatusCode.RECRUITMENT_REQUEST)
                .printDateTime(LocalDateTime.now())
                .workPlace(request.getWorkPlace())
                .workingHour(request.getWorkingHour())
                .reportingTime(request.getReportingTime())
                .trainingPay(request.getTrainingPay())
                .fullTimePay(request.getFullTimePay())
                .recruitmentDate(RecruitmentDate.builder()
                        .requestBeginDate(LocalDate.now())
                        .recruitBeginDate(request.getRecruitBeginDate())
                        .recruitEndDate(request.getRecruitEndDate())
                        .build())
                .otherLanguage(request.getOtherLanguage())
                .otherTechnology(request.getOtherTechnology())
                .preferential(request.getPreferential())
                .report(request.getReport())
                .qualification(request.getQualification())
                .otherSpecifics(request.getOtherSpecifics())
                .form(Form.builder()
                        .form1(wrapNullableAttachment(request.getForm1()))
                        .form2(wrapNullableAttachment(request.getForm2()))
                        .form3(wrapNullableAttachment(request.getForm3()))
                        .build())
                .documentation(request.getDocumentation())
                .build());

        request.getHiringAreas().forEach(hiringArea -> recruitmentFacade.addHiringArea(hiringArea, recruitment));
        request.getTags().forEach(name -> recruitmentFacade.addTag(name, recruitment));
        IntStream.range(0, request.getScreeningProcesses().size())
                .forEach(idx -> recruitmentFacade.addScreeningProcess(request.getScreeningProcesses().get(idx), idx+1, recruitment));
        request.getTechnologies().forEach(code -> recruitmentFacade.addTechnology(code, recruitment));
        request.getWelfare().forEach(code -> recruitmentFacade.addWelfare(code, recruitment));
        request.getProgrammingLanguages().forEach(code -> recruitmentFacade.addProgrammingLanguage(code, recruitment));
    }

    private Attachment wrapNullableAttachment(Integer id) {
        return id == null ? null : attachmentFacade.findById(id);
    }
}
