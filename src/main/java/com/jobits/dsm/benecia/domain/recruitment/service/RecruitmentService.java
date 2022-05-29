package com.jobits.dsm.benecia.domain.recruitment.service;

import com.jobits.dsm.benecia.domain.application.domain.ApplicationAttachmentRepository;
import com.jobits.dsm.benecia.domain.application.domain.ApplicationRepository;
import com.jobits.dsm.benecia.domain.recruitment.code.RecruitmentStatusCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.RecruitmentRepository;
import com.jobits.dsm.benecia.domain.recruitment.domain.programminglanguage.ProgrammingLanguageRepository;
import com.jobits.dsm.benecia.domain.recruitment.domain.screeningprocess.ScreeningProcessRepository;
import com.jobits.dsm.benecia.domain.recruitment.domain.tag.TagRepository;
import com.jobits.dsm.benecia.domain.recruitment.domain.technology.TechnologyRepository;
import com.jobits.dsm.benecia.domain.recruitment.domain.vo.RecruitmentDetailVO;
import com.jobits.dsm.benecia.domain.recruitment.domain.welfare.WelfareRepository;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.request.*;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.jobits.dsm.benecia.domain.attachment.domain.Attachment;
import com.jobits.dsm.benecia.domain.attachment.facade.AttachmentFacade;
import com.jobits.dsm.benecia.domain.recruitment.domain.*;
import com.jobits.dsm.benecia.domain.recruitment.facade.RecruitmentFacade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final TagRepository tagRepository;
    private final ScreeningProcessRepository screeningProcessRepository;
    private final TechnologyRepository technologyRepository;
    private final ApplicationRepository applicationRepository;
    private final ApplicationAttachmentRepository applicationAttachmentRepository;
    private final WelfareRepository welfareRepository;
    private final ProgrammingLanguageRepository programmingLanguageRepository;
    private final RecruitmentFacade recruitmentFacade;
    private final AttachmentFacade attachmentFacade;

    public RecruitmentInfoListForTeacherResponse getRecruitmentInfoList(RecruitmentInfoListForTeacherRequest request) {
        return RecruitmentInfoListForTeacherResponse.builder()
                .recruitments(recruitmentRepository.getRecruitmentInfoList(request.getRecruitmentYear(), request.getKeyword(), request.getRecruitStatus(), request.getBeginDate(), request.getEndDate()) // null 처리 추가 요망
                        .stream().map(recruitment -> RecruitmentInfoListForTeacherResponse.RecruitmentInfo.builder()
                                .registrationNumber(recruitment.getRegistrationNumber())
                                .receptionYear(recruitment.getReceptionYear())
                                .status(recruitment.getStatus())
                                .name(recruitment.getName())
                                .hiring(recruitment.getHiring())
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
                .recruitments(recruitmentRepository.getCurrentRecruitmentInfoList(request.getTagIds(), request.getHiringCodes(), request.getKeyword(), request.getRegionId(), request.getSort())
                        .stream().map(recruitment -> CurrentRecruitmentInfoListForStudentResponse.CurrentRecruitmentInfo.builder()
                                .hiring(CurrentRecruitmentInfoListForStudentResponse.HiringInfo.builder()
                                        .id(recruitment.getHiringId())
                                        .code(recruitment.getHiringCode())
                                        .build()
                                )
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

        request.getHiring().forEach(hiringArea -> recruitmentFacade.addHiringArea(hiringArea, recruitment));
        request.getTags().forEach(name -> recruitmentFacade.addTag(name, recruitment));
        IntStream.range(0, request.getScreeningProcesses().size())
                .forEach(idx -> recruitmentFacade.addScreeningProcess(request.getScreeningProcesses().get(idx), idx + 1, recruitment));
        request.getTechnologies().forEach(code -> recruitmentFacade.addTechnology(code, recruitment));
        request.getWelfare().forEach(code -> recruitmentFacade.addWelfare(code, recruitment));
        request.getProgrammingLanguages().forEach(code -> recruitmentFacade.addProgrammingLanguage(code, recruitment));
    }

    @Transactional(readOnly = true)
    public RecruitmentDetailResponse queryRecruitmentDetail(Integer hiringId) {
        RecruitmentDetailVO recruitmentDetailVO = recruitmentRepository.queryRecruitmentDetail(hiringId);
        return RecruitmentDetailResponse.builder()
                .registrationNumber(recruitmentDetailVO.getRegistrationNumber())
                .receptionYear(recruitmentDetailVO.getReceptionYear())
                .region(recruitmentDetailVO.getRegion())
                .enterpriseBackgroundImageUrl(recruitmentDetailVO.getEnterpriseBackgroundImageUrl())
                .enterpriseProfileImageUrl(recruitmentDetailVO.getEnterpriseProfileImageUrl())
                .introduce(recruitmentDetailVO.getIntroduce())
                .enterpriseName(recruitmentDetailVO.getEnterpriseName())
                .workPlace(recruitmentDetailVO.getWorkPlace())
                .hiring(RecruitmentDetailResponse.HiringInfo.builder()
                        .hiringId(recruitmentDetailVO.getHiringId())
                        .code(recruitmentDetailVO.getHiringCode().name())
                        .task(recruitmentDetailVO.getTask())
                        .build()
                )
                .recruitCount(recruitmentDetailVO.getRecruitCount())
                .tags(tagRepository.findAllByRecruitment(recruitmentDetailVO.getReceptionYear(), recruitmentDetailVO.getRegistrationNumber()))
                .screeningProcesses(screeningProcessRepository.findAllByRecruitment(recruitmentDetailVO.getReceptionYear(), recruitmentDetailVO.getRegistrationNumber()))
                .technologies(technologyRepository.findAllByRecruitment(recruitmentDetailVO.getReceptionYear(), recruitmentDetailVO.getRegistrationNumber()))
                .welfare(welfareRepository.findAllByRecruitment(recruitmentDetailVO.getReceptionYear(), recruitmentDetailVO.getRegistrationNumber()))
                .programmingLanguages(programmingLanguageRepository.findAllByRecruitment(recruitmentDetailVO.getReceptionYear(), recruitmentDetailVO.getRegistrationNumber()))
                .workingHour(recruitmentDetailVO.getWorkingHour())
                .reportingTime(recruitmentDetailVO.getReportingTime().name())
                .trainingPay(recruitmentDetailVO.getTrainingPay())
                .fullTimePay(recruitmentDetailVO.getFullTimePay().name())
                .recruitBeginDate(recruitmentDetailVO.getRecruitBeginDate())
                .recruitEndDate(recruitmentDetailVO.getRecruitEndDate())
                .otherLanguage(recruitmentDetailVO.getOtherLanguage())
                .otherTechnology(recruitmentDetailVO.getOtherTechnology())
                .preferential(recruitmentDetailVO.getPreferential())
                .report(recruitmentDetailVO.getReport())
                .qualification(recruitmentDetailVO.getQualification())
                .otherSpecifics(recruitmentDetailVO.getOtherSpecifics())
                .documentationType(RecruitmentDetailResponse.DocumentationType.builder()
                        .documentation1(recruitmentDetailVO.getDocumentation1())
                        .documentation2(recruitmentDetailVO.getDocumentation2())
                        .documentation3(recruitmentDetailVO.getDocumentation3())
                        .build()
                )
                .form1(recruitmentDetailVO.getForm1())
                .form2(recruitmentDetailVO.getForm2())
                .form3(recruitmentDetailVO.getForm3())
                .build();
    }

    public AllRecruitmentInfoListForStudentResponse queryAllRecruitmentInfoList(AllRecruitmentInfoListForStudentRequest request) {
        return AllRecruitmentInfoListForStudentResponse.builder()
                .recruitments(recruitmentRepository.queryAllRecruitmentInfoList(request.getTagIds(), request.getHiringCodes(), request.getKeyword())
                        .stream().map(recruitment -> AllRecruitmentInfoListForStudentResponse.AllRecruitmentInfo.builder()
                                .hiring(AllRecruitmentInfoListForStudentResponse.HiringInfo.builder()
                                        .id(recruitment.getHiringId())
                                        .code(recruitment.getHiringCode())
                                        .build()
                                )
                                .recruitCount(recruitment.getRecruitCount())
                                .enterpriseName(recruitment.getEnterpriseName())
                                .introduction(recruitment.getIntroduction())
                                .tags(recruitment.getTags())
                                .enterpriseProfileImageUrl(recruitment.getEnterpriseProfileImageUrl())
                                .isEnd(LocalDate.now().isAfter(recruitment.getRecruitEndDate()))
                                .build()
                        ).collect(Collectors.toList()))
                .build();
    }

    public SimilarRecruitmentInfoListForStudentResponse querySimilarRecruitmentInfoList(SimilarRecruitmentInfoListForStudentRequest request) {
        return SimilarRecruitmentInfoListForStudentResponse.builder()
                .recruitments(recruitmentRepository.querySimilarRecruitmentInfoList(request.getHiringCodes(), request.getRegion())
                        .stream().map(recruitment -> SimilarRecruitmentInfoListForStudentResponse.SimilarRecruitmentInfo.builder()
                                .hiring(SimilarRecruitmentInfoListForStudentResponse.HiringInfo.builder()
                                        .id(recruitment.getHiringId())
                                        .code(recruitment.getHiringCode())
                                        .build()
                                )
                                .recruitCount(recruitment.getRecruitCount())
                                .enterpriseName(recruitment.getEnterpriseName())
                                .workPlace(recruitment.getWorkPlace())
                                .tags(recruitment.getTags())
                                .enterpriseProfileImageUrl(recruitment.getEnterpriseProfileImageUrl())
                                .enterpriseBackgroundImageUrl(recruitment.getEnterpriseBackgroundImageUrl())
                                .build()
                        ).collect(Collectors.toList()))
                .build();
    }

    @Transactional
    public void modifyRecruitment(ModifyRecruitmentRequest request) {
        String registrationNumber = SecurityContextHolder.getContext().getAuthentication().getName();

        RecruitmentId recruitmentId = new RecruitmentId(request.getReceptionYear(), registrationNumber);

        recruitmentRepository.deleteById(recruitmentId);

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

        request.getHiring().forEach(hiringArea -> recruitmentFacade.addHiringArea(hiringArea, recruitment));
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
