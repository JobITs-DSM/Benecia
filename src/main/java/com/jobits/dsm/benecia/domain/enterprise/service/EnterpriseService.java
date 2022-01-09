package com.jobits.dsm.benecia.domain.enterprise.service;

import com.jobits.dsm.benecia.domain.attatchment.facade.AttachmentFacade;
import com.jobits.dsm.benecia.domain.enterprise.code.BusinessAreaCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseDivisionCode;
import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import com.jobits.dsm.benecia.domain.enterprise.domain.EnterpriseRepository;
import com.jobits.dsm.benecia.domain.enterprise.domain.businessarea.BusinessArea;
import com.jobits.dsm.benecia.domain.enterprise.domain.businessarea.BusinessAreaRepository;
import com.jobits.dsm.benecia.domain.enterprise.domain.cache.EnterpriseRefreshToken;
import com.jobits.dsm.benecia.domain.enterprise.domain.cache.EnterpriseRefreshTokenRepository;
import com.jobits.dsm.benecia.domain.enterprise.exceptions.EnterpriseNotFoundException;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.EnterpriseSignInRequest;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.ModifyEnterpriseInfoRequest;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.RegisterEnterpriseRequest;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.EnterpriseInfoListResponse;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.EnterpriseInfoResponse;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.EnterpriseInfoResponse.AttachmentDetails;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.EnterpriseTokenResponse;
import com.jobits.dsm.benecia.global.security.dto.Tokens;
import com.jobits.dsm.benecia.global.security.jwt.JwtTokenProvider;
import com.jobits.dsm.benecia.global.security.property.JwtProperty;
import com.jobits.dsm.benecia.global.security.property.JwtRoleProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;
    private final EnterpriseRefreshTokenRepository refreshTokenRepository;
    private final BusinessAreaRepository businessAreaRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperty jwtProperty;

    private final AttachmentFacade attachmentFacade;

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

        request.getBusinessAreas()
                .forEach(businessAreaCode -> enterprise.getBusinessAreas()
                        .add(businessAreaRepository.save(BusinessArea.builder()
                                .code(businessAreaCode)
                                .enterprise(enterprise)
                                .build())));

        attachmentFacade.saveAttachment(enterprise, request.getBusinessLicense(), enterprise::setBusinessLicense);
        attachmentFacade.saveAttachment(enterprise, request.getLogo(), enterprise::setLogo);
        attachmentFacade.saveAttachment(enterprise, request.getMaterial(), enterprise::setMaterial);
        attachmentFacade.saveAttachment(enterprise, request.getForeground(), enterprise::setForeground);
    }

    @Transactional
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

    public EnterpriseInfoListResponse getEnterpriseInfoList() {
        return EnterpriseInfoListResponse.builder()
                .enterprises(enterpriseRepository.findAll()
                        .stream().map(enterprise -> EnterpriseInfoListResponse.of(
                                enterprise.getRegistrationNumber(),
                                enterprise.getName(),
                                enterprise.getAddress().getPostalCode(),
                                enterprise.getEmployeeCount(),
                                enterprise.getTurnover(),
                                enterprise.getDivision(),
                                enterprise.getIsConvention(),
                                enterpriseRepository.getBusinessAreas(enterprise.getRegistrationNumber())
                                        .stream().map(BusinessAreaCode::getValue)
                                        .collect(Collectors.toList()),
                                enterprise.getLastReceptionYear(),
                                enterpriseRepository.getContractStudentCount(enterprise.getRegistrationNumber()),
                                enterpriseRepository.getReviewCount(enterprise.getRegistrationNumber())
                        )).collect(Collectors.toList()))
                .build();
    }
}
