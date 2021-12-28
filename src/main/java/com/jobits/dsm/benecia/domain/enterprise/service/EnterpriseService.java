package com.jobits.dsm.benecia.domain.enterprise.service;

import com.jobits.dsm.benecia.domain.attatchment.domain.Attachment;
import com.jobits.dsm.benecia.domain.attatchment.domain.AttachmentRepository;
import com.jobits.dsm.benecia.domain.enterprise.code.BusinessAreaCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseDivisionCode;
import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import com.jobits.dsm.benecia.domain.enterprise.domain.EnterpriseRepository;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.RegisterEnterpriseRequest;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.EnterpriseListResponse;
import com.jobits.dsm.benecia.infrastructure.s3.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EnterpriseService {

    private final S3Util s3Util;

    private final EnterpriseRepository enterpriseRepository;
    private final AttachmentRepository attachmentRepository;

    @Transactional
    public void registerEnterprise(RegisterEnterpriseRequest request) {
        Enterprise enterprise = enterpriseRepository.save(Enterprise.builder()
                .registrationNumber(request.getRegistrationNumber())
                .name(request.getName())
                .establishYear(request.getEstablishYear())
                .isConvention(false)
                .division(EnterpriseDivisionCode.PARTICIPATE_COMPANY)
                .representativeName(request.getRepresentativeName())
                .address(request.getAddress())
                .branchAddress(request.getBranch())
                .introduction(request.getIntroduction())
                .employeeCount(request.getEmployeeCount())
                .site(request.getSite())
                .turnover(request.getTurnover())
                .director(request.getDirector())
                .build());

        saveEnterpriseAttachment(enterprise, request.getBusinessLicense(), enterprise::setBusinessLicense);
        saveEnterpriseAttachment(enterprise, request.getLogo(), enterprise::setLogo);
        saveEnterpriseAttachment(enterprise, request.getMaterial(), enterprise::setMaterial);
        saveEnterpriseAttachment(enterprise, request.getForeground(), enterprise::setForeground);
    }

    public EnterpriseListResponse getEnterpriseList() {
        List<String> code = enterpriseRepository.getBusinessAreas("305-83-23458")
                .stream().map(BusinessAreaCode::getValue)
                .collect(Collectors.toList());

        return EnterpriseListResponse.builder()
                .enterprises(enterpriseRepository.findAll()
                        .stream().map(enterprise -> EnterpriseListResponse.of(
                                enterprise.getRegistrationNumber(),
                                enterprise.getName(),
                                enterprise.getAddress().getPostalCode(),
                                enterprise.getEmployeeCount(),
                                enterprise.getTurnover(),
                                enterprise.getDivision(),
                                enterprise.getIsConvention(),
                                enterpriseRepository.getBusinessAreas("305-83-23458")
                                        .stream().map(BusinessAreaCode::getValue)
                                        .collect(Collectors.toList()),
                                enterprise.getLastReceptionYear(),
                                enterpriseRepository.getContractStudentCount(enterprise.getRegistrationNumber()),
                                enterpriseRepository.getReviewCount(enterprise.getRegistrationNumber())
                        )).collect(Collectors.toList()))
                .build();
    }

    private void saveEnterpriseAttachment(Enterprise enterprise, MultipartFile file, Consumer<Attachment> consumer) {
        Optional<String> savedFile = saveFileToStorage(file, "enterprise" + "/" + enterprise.getRegistrationNumber());
        if (savedFile.isPresent()) {
            Attachment attachment = saveFileToDatabase(savedFile.get(), file.getOriginalFilename());
            consumer.accept(attachment);
        }
    }

    private Optional<String> saveFileToStorage(MultipartFile file, String directoryName) {
        if (file == null || file.isEmpty() || file.getOriginalFilename() == null) {
            return Optional.empty();
        }
        return Optional.of(s3Util.saveFile(file, directoryName));
    }

    private Attachment saveFileToDatabase(String fileName, String originalFileName) {
        return attachmentRepository.save(Attachment.builder()
                .fileName(fileName)
                .originalFileName(originalFileName)
                .build());
    }
}
