package com.jobits.dsm.benecia.domain.enterprise.service;

import com.jobits.dsm.benecia.domain.attachment.domain.Attachment;
import com.jobits.dsm.benecia.domain.attachment.domain.AttachmentRepository;
import com.jobits.dsm.benecia.domain.attachment.facade.AttachmentFacade;
import com.jobits.dsm.benecia.domain.enterprise.code.BusinessAreaCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseDivisionCode;
import com.jobits.dsm.benecia.domain.enterprise.domain.Address;
import com.jobits.dsm.benecia.domain.enterprise.domain.Director;
import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import com.jobits.dsm.benecia.domain.enterprise.domain.EnterpriseRepository;
import com.jobits.dsm.benecia.domain.enterprise.domain.businessarea.BusinessArea;
import com.jobits.dsm.benecia.domain.enterprise.domain.businessarea.BusinessAreaRepository;
import com.jobits.dsm.benecia.domain.enterprise.domain.cache.EnterpriseRefreshToken;
import com.jobits.dsm.benecia.domain.enterprise.domain.cache.EnterpriseRefreshTokenRepository;
import com.jobits.dsm.benecia.domain.enterprise.domain.region.RegionRepository;
import com.jobits.dsm.benecia.domain.enterprise.exceptions.EnterpriseNotFoundException;
import com.jobits.dsm.benecia.domain.enterprise.exceptions.RegionNotFoundException;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.EnterpriseSignInRequest;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.ModifyEnterpriseInfoRequest;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.RegisterEnterpriseRequest;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.EnterpriseInfoListResponse;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.EnterpriseInfoResponse;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.EnterpriseInfoResponse.AttachmentDetails;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.EnterpriseTokenResponse;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.LeadingEnterpriseListResponse;
import com.jobits.dsm.benecia.global.security.dto.Tokens;
import com.jobits.dsm.benecia.global.security.jwt.JwtTokenProvider;
import com.jobits.dsm.benecia.global.security.property.JwtProperty;
import com.jobits.dsm.benecia.global.security.property.JwtRoleProperty;
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
    private final EnterpriseRefreshTokenRepository refreshTokenRepository;
    private final BusinessAreaRepository businessAreaRepository;
    private final AttachmentRepository attachmentRepository;
    private final RegionRepository regionRepository;
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
                .region(regionRepository.findById(request.getRegion())
                        .orElseThrow(() -> RegionNotFoundException.EXCEPTION))
                .turnover(request.getTurnover())
                .director(request.getDirector())
                .businessLicense(attachmentFacade.findById(request.getBusinessLicense()))
                .logo(request.getLogo() != null ? attachmentFacade.findById(request.getLogo()) : attachmentFacade.findById(1))
                .material(request.getMaterial() != null ? attachmentFacade.findById(request.getMaterial()) : null)
                .foreground(request.getForeground() != null ? attachmentFacade.findById(request.getForeground()) : attachmentFacade.findById(1))
                .build());

        request.getBusinessAreas()
                .forEach(businessAreaCode -> {
                    enterprise.getBusinessAreas().add(businessAreaRepository.save(BusinessArea.builder()
                            .code(businessAreaCode)
                            .enterprise(enterprise)
                            .build()));
                });
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

    public EnterpriseInfoResponse getEnterpriseInfo(String registrationNumber) {
        Enterprise enterprise = enterpriseRepository.findById(registrationNumber)
                .orElseThrow(() -> EnterpriseNotFoundException.EXCEPTION);
        return EnterpriseInfoResponse.builder()
                        .registrationNumber(enterprise.getRegistrationNumber())
                        .name(enterprise.getName())
                        .establishYear(enterprise.getEstablishYear())
                        .representativeName(enterprise.getRepresentativeName())
                        .address(enterprise.getAddress())
                        .branch(enterprise.getBranchAddress())
                        .introduction(enterprise.getIntroduction())
                        .employeeCount(enterprise.getEmployeeCount().getValue())
                        .site(enterprise.getSite())
                        .turnover(enterprise.getTurnover())
                        .director(enterprise.getDirector())
                        .businessAreas(enterprise.getBusinessAreas()
                                .stream().map(code -> code.getCode().getValue())
                                .collect(Collectors.toList()))
                        .businessLicense(checkNull(enterprise.getBusinessLicense()))
                        .logo(checkNull(enterprise.getLogo()))
                        .material(checkNull(enterprise.getMaterial()))
                        .foreground(checkNull(enterprise.getForeground()))
                        .build();
    }

    private AttachmentDetails checkNull(Attachment attachment) {
        if (attachment == null) {
            return null;
        }
        return new AttachmentDetails(attachment.getId(), attachment.getFileName(), attachment.getOriginalFileName());
    }

    @Transactional
    public void modifyEnterpriseInfo(String registrationNumber, ModifyEnterpriseInfoRequest request) {
        Enterprise enterprise = enterpriseRepository.findById(registrationNumber)
                .orElseThrow(() -> EnterpriseNotFoundException.EXCEPTION);

        Enterprise modifiedEnterprise = Enterprise.builder()
                .registrationNumber(registrationNumber)
                .name(request.getName())
                .establishYear(request.getEstablishYear())
                .representativeName(request.getRepresentativeName())
                .address(Address.builder()
                        .postalCode(request.getPostalCode())
                        .address(request.getAddress())
                        .addressDetail(request.getAddressDetail())
                        .build()
                )
                .branchAddress(Address.builder()
                        .postalCode(request.getBranchPostalCode())
                        .address(request.getAddress())
                        .addressDetail(request.getBranchAddressDetail())
                        .build()
                )
                .introduction(request.getIntroduction())
                .employeeCount(request.getEmployeeCount())
                .site(request.getSite())
                .turnover(request.getTurnover())
                .director(Director.builder()
                        .email(request.getDirectorEmail())
                        .name(request.getDirectorName())
                        .telephoneNumber(request.getDirectorTelephoneNumber())
                        .phoneNumber(request.getDirectorPhoneNumber())
                        .department(request.getDirectorDepartment())
                        .build()
                )
                .businessLicense(attachmentFacade.findById(request.getBusinessLicense()))
                .logo(attachmentFacade.findById(request.getLogo()))
                .material(request.getMaterial() != null ? attachmentFacade.findById(request.getMaterial()) : null)
                .foreground(attachmentFacade.findById(request.getForeground()))
                .region(regionRepository.findByName(request.getRegion()).orElseThrow(() -> RegionNotFoundException.EXCEPTION))
                .build();

        enterpriseRepository.save(modifiedEnterprise);

        businessAreaRepository.deleteAllByEnterprise(enterprise);

        enterprise.getBusinessAreas().clear();

        request.getBusinessAreas()
                .forEach(businessAreaCode -> {
                    enterprise.getBusinessAreas().add(businessAreaRepository.save(BusinessArea.builder()
                            .code(businessAreaCode)
                            .enterprise(enterprise)
                            .build()));
                });
    }

    public List<LeadingEnterpriseListResponse> getLeadingEnterprise() {
        List<Enterprise> enterprises = enterpriseRepository.findAll();

        return enterprises.stream().map(enterprise -> LeadingEnterpriseListResponse.builder()
                .name(enterprise.getName())
                .region(enterprise.getRegion().getName())
                .build()
        ).collect(Collectors.toList());
    }
}
