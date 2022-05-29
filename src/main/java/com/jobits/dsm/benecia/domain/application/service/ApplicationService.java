package com.jobits.dsm.benecia.domain.application.service;

import com.jobits.dsm.benecia.domain.application.code.ApplicationCode;
import com.jobits.dsm.benecia.domain.application.domain.Application;
import com.jobits.dsm.benecia.domain.application.domain.ApplicationAttachment;
import com.jobits.dsm.benecia.domain.application.domain.ApplicationAttachmentRepository;
import com.jobits.dsm.benecia.domain.application.domain.ApplicationRepository;
import com.jobits.dsm.benecia.domain.application.presentation.payload.request.ApplyEnterpriseRequest;
import com.jobits.dsm.benecia.domain.attachment.domain.Attachment;
import com.jobits.dsm.benecia.domain.attachment.domain.AttachmentRepository;
import com.jobits.dsm.benecia.domain.attachment.exceptions.AttachmentNotFoundException;
import com.jobits.dsm.benecia.domain.recruitment.domain.Recruitment;
import com.jobits.dsm.benecia.domain.recruitment.domain.RecruitmentId;
import com.jobits.dsm.benecia.domain.recruitment.domain.RecruitmentRepository;
import com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea.HiringArea;
import com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea.HiringAreaRepository;
import com.jobits.dsm.benecia.domain.recruitment.exceptions.HiringAreaNotFoundException;
import com.jobits.dsm.benecia.domain.recruitment.exceptions.RecruitmentNotFoundException;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response.QueryApplicantListResponse;
import com.jobits.dsm.benecia.domain.student.domain.Student;
import com.jobits.dsm.benecia.domain.student.domain.StudentRepository;
import com.jobits.dsm.benecia.domain.student.facade.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final HiringAreaRepository hiringAreaRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final ApplicationAttachmentRepository applicationAttachmentRepository;
    private final AttachmentRepository attachmentRepository;
    private final StudentRepository studentRepository;
    private final StudentFacade studentFacade;

    @Transactional
    public void applyEnterprise(ApplyEnterpriseRequest request) {
        Student student = studentFacade.getCurrentUser();

        HiringArea hiringArea = hiringAreaRepository.findById(request.getHiringId())
                .orElseThrow(() -> HiringAreaNotFoundException.EXCEPTION);

        Recruitment recruitment = recruitmentRepository.findById(new RecruitmentId(request.getReceptionYear(), request.getRegistrationNumber()))
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        Application application = applicationRepository.save(Application.builder()
                .status(ApplicationCode.UNDER_REVIEW)
                .studentSerialNumber(student)
                .hiringAreaId(hiringArea)
                .recruitment(recruitment)
                .build()
        );

        List<ApplicationAttachment> attachments = new ArrayList<>();

        for (Integer id : request.getAttachmentId()) {
            attachments.add(ApplicationAttachment.builder()
                    .application(application)
                    .attachment(attachmentRepository.findById(id)
                            .orElseThrow(() -> AttachmentNotFoundException.EXCEPTION))
                    .build());
        }

        applicationAttachmentRepository.saveAll(attachments);

    }

    public QueryApplicantListResponse queryApplicantList(String registrationNumber, String receptionYear) {
        return QueryApplicantListResponse.builder()
                .studentApplications(applicationRepository.queryApplicantList(registrationNumber, receptionYear)
                        .stream().map(application -> QueryApplicantListResponse.ApplicationInfo.builder()
                                .studentNumber(application.getStudentNumber())
                                .studentName(application.getStudentName())
                                .dateTime(application.getDateTime())
                                .attachments(buildAttachmentInfo(application.getAttachments()))
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    private List<QueryApplicantListResponse.AttachmentInfo> buildAttachmentInfo(List<Attachment> attachment) {
        return attachment.stream()
                .map(attachmentInfo -> QueryApplicantListResponse.AttachmentInfo.builder()
                        .name(attachmentInfo.getOriginalFileName())
                        .fileUrl(attachmentInfo.getFileName())
                        .build()
                ).collect(Collectors.toList());
    }
}
