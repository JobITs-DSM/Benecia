package com.jobits.dsm.benecia.domain.recruitment.service;

import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.RecruitmentRepository;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request.CurrentRecruitmentInfoListForStudentRequest;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request.RecruitmentInfoListForTeacherRequest;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response.CurrentRecruitmentInfoListForStudentResponse;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response.RecruitmentInfoListForTeacherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.jobits.dsm.benecia.domain.attachment.domain.Attachment;
import com.jobits.dsm.benecia.domain.attachment.facade.AttachmentFacade;
import com.jobits.dsm.benecia.domain.recruitment.domain.*;
import com.jobits.dsm.benecia.domain.recruitment.facade.RecruitmentFacade;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request.CreateRecruitmentRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentFacade recruitmentFacade;
    private final AttachmentFacade attachmentFacade;

    public RecruitmentInfoListForTeacherResponse getRecruitmentInfoList(RecruitmentInfoListForTeacherRequest request) {
        RecruitmentStatusCode recruitmentStatus = RecruitmentStatusCode.find(request.getRecruitStatus());
        LocalDate beginDate = LocalDate.parse(request.getBeginDate(), DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(request.getEndDate(), DateTimeFormatter.ISO_DATE);
        return RecruitmentInfoListForTeacherResponse.builder()
                .recruitments(recruitmentRepository.getRecruitmentInfoList(request.getRecruitmentYear(), request.getKeyword(), recruitmentStatus, beginDate, endDate) // null 처리 추가 요망
                        .stream().map(recruitment -> RecruitmentInfoListForTeacherResponse.RecruitmentInfo.builder()
                                .status(recruitment.getStatus())
                                .name(recruitment.getName())
                                .hiring(recruitment.getHiring().stream().map(HiringAreaCode::getValue).collect(Collectors.toList()))
                                .recruitCount(recruitment.getRecruitmentCount())
                                .applicantCount(recruitment.getApplicantCount())
                                .division(recruitment.getDivision())
                                .recruitBeginDate(recruitment.getRecruitBeginDate())
                                .recruitEndDate(recruitment.getRecruitEndDate())
                                .build()
                        ).collect(Collectors.toList()))
                .build();
    }

    public CurrentRecruitmentInfoListForStudentResponse getCurrentRecruitmentInfoList(CurrentRecruitmentInfoListForStudentRequest request) {
        return CurrentRecruitmentInfoListForStudentResponse.builder()
                .recruitments(recruitmentRepository.getCurrentRecruitmentInfoList(request.getTagIds(), request.getHirings(), request.getKeyword(), request.getRegionId(), request.getSort())
                        .stream().map(recruitment -> CurrentRecruitmentInfoListForStudentResponse.CurrentRecruitmentInfo.builder()
                                .hiring(recruitment.getHiring().getValue())
                                .recruitCount(recruitment.getRecruitCount())
                                .enterpriseName(recruitment.getEnterpriseName())
                                .workPlace(recruitment.getWorkPlace())
                                .tags(recruitment.getTags())
                                .enterpriseBackgroundImageUrl(recruitment.getEnterpriseBackgroundImageUrl())
                                .enterpriseProfileImageUrl(recruitment.getEnterpriseProfileImageUrl())
                                .build()
                        ).collect(Collectors.toList()))
                .build();
    }

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
                .recruitCount(request.getRecruitCount())
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
                .forEach(idx -> recruitmentFacade.addScreeningProcess(request.getScreeningProcesses().get(idx), idx + 1, recruitment));
        request.getTechnologies().forEach(code -> recruitmentFacade.addTechnology(code, recruitment));
        request.getWelfare().forEach(code -> recruitmentFacade.addWelfare(code, recruitment));
        request.getProgrammingLanguages().forEach(code -> recruitmentFacade.addProgrammingLanguage(code, recruitment));
    }

    private Attachment wrapNullableAttachment(Integer id) {
        return id == null ? null : attachmentFacade.findById(id);
    }
}
