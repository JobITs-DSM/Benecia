package com.jobits.dsm.benecia.domain.enterprise.service;

import com.jobits.dsm.benecia.domain.attatchment.domain.Attachment;
import com.jobits.dsm.benecia.domain.attatchment.domain.AttachmentRepository;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseDivisionCode;
import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import com.jobits.dsm.benecia.domain.enterprise.domain.EnterpriseRepository;
<<<<<<< HEAD
import com.jobits.dsm.benecia.domain.enterprise.domain.cache.EnterpriseRefreshToken;
import com.jobits.dsm.benecia.domain.enterprise.domain.cache.EnterpriseRefreshTokenRepository;
import com.jobits.dsm.benecia.domain.enterprise.exceptions.EnterpriseNotFoundException;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.EnterpriseSignInRequest;
=======
import com.jobits.dsm.benecia.domain.enterprise.domain.businessarea.BusinessArea;
import com.jobits.dsm.benecia.domain.enterprise.domain.businessarea.BusinessAreaRepository;
>>>>>>> c935aee6affbf195746ab074fdc9553a45d1a4bb
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.RegisterEnterpriseRequest;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.EnterpriseTokenResponse;
import com.jobits.dsm.benecia.global.security.dto.Tokens;
import com.jobits.dsm.benecia.global.security.jwt.JwtTokenProvider;
import com.jobits.dsm.benecia.global.security.property.JwtProperty;
import com.jobits.dsm.benecia.global.security.property.JwtRoleProperty;
import com.jobits.dsm.benecia.infrastructure.s3.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Service
public class EnterpriseService {

    private final S3Util s3Util;

    private final EnterpriseRepository enterpriseRepository;
    private final EnterpriseRefreshTokenRepository refreshTokenRepository;
    private final BusinessAreaRepository businessAreaRepository;
    private final AttachmentRepository attachmentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperty jwtProperty;

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

        request.getBusinessAreas()
                .forEach(businessAreaCode -> {
                    enterprise.getBusinessAreas().add(businessAreaRepository.save(BusinessArea.builder()
                            .code(businessAreaCode)
                            .enterprise(enterprise)
                            .build()));
                });
    }

    @Transactional(readOnly = true)
    public EnterpriseTokenResponse enterpriseSignIn(EnterpriseSignInRequest request) {
        Enterprise enterprise = enterpriseRepository.findById(request.getRegistrationNumber())
                        .orElseThrow(() -> EnterpriseNotFoundException.EXCEPTION);

        Tokens tokens = jwtTokenProvider.getToken(request.getRegistrationNumber(), JwtRoleProperty.ENTERPRISE_ROLE);

        EnterpriseRefreshToken refreshToken = EnterpriseRefreshToken.builder()
                .refreshExp(jwtProperty.getExp().getRefresh())
                .registrationNumber(enterprise.getRegistrationNumber())
                .refreshToken(tokens.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return EnterpriseTokenResponse.builder()
                .accessToken(tokens.getAccessToken())
                .refreshToken(tokens.getRefreshToken())
                .build();
    }

    private void saveEnterpriseAttachment(Enterprise enterprise, MultipartFile file, Consumer<Attachment> consumer) {
        Optional<String> savedFile = saveFileToStorage(file, "enterprise" + "/" + enterprise.getRegistrationNumber());
        if(savedFile.isPresent()) {
            Attachment attachment = saveFileToDatabase(savedFile.get(), file.getOriginalFilename());
            consumer.accept(attachment);
        }
    }

    private Optional<String> saveFileToStorage(MultipartFile file, String directoryName) {
        if(file == null || file.isEmpty() || file.getOriginalFilename() == null) {
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
