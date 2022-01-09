package com.jobits.dsm.benecia.domain.enterprise.domain;

import com.jobits.dsm.benecia.domain.enterprise.code.BusinessAreaCode;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request.ModifyEnterpriseInfoRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.jobits.dsm.benecia.domain.contract.domain.QContract.contract;
import static com.jobits.dsm.benecia.domain.enterprise.domain.QEnterprise.enterprise;
import static com.jobits.dsm.benecia.domain.enterprise.domain.businessarea.QBusinessArea.businessArea;
import static com.jobits.dsm.benecia.domain.recruitment.domain.QRecruitment.recruitment;
import static com.jobits.dsm.benecia.domain.review.domain.QReview.review;

@RequiredArgsConstructor
public class EnterpriseRepositoryCustomImpl implements EnterpriseRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<BusinessAreaCode> getBusinessAreas(String registrationNumber) {
        return queryFactory
                .select(businessArea.code)
                .from(businessArea)
                .join(businessArea.enterprise, enterprise)
                .where(enterprise.registrationNumber.eq(registrationNumber))
                .fetch();
    }

    @Override
    public Long getContractStudentCount(String registrationNumber) {
        return queryFactory
                .selectFrom(contract)
                .join(contract.recruitment, recruitment)
                .where(recruitment.recruitmentId.registrationNumber.eq(registrationNumber))
                .fetchCount();
    }

    @Override
    public Long getReviewCount(String registrationNumber) {
        return queryFactory
                .selectFrom(review)
                .join(review.enterprise, enterprise)
                .where(enterprise.registrationNumber.eq(registrationNumber))
                .fetchCount();
    }

    @Override
    public void modifyEnterpriseInfo(String registrationNumber, ModifyEnterpriseInfoRequest request) {
        queryFactory
                .update(enterprise)
                .set(enterprise.name, request.getName())
                .set(enterprise.establishYear, request.getEstablishYear())
                .set(enterprise.representativeName, request.getRepresentativeName())
                .set(enterprise.address.postalCode, request.getPostalCode())
                .set(enterprise.address.address, request.getAddress())
                .set(enterprise.address.addressDetail, request.getAddressDetail())
                .set(enterprise.branchAddress.postalCode, request.getBranchPostalCode())
                .set(enterprise.branchAddress.address, request.getBranchAddress())
                .set(enterprise.branchAddress.addressDetail, request.getBranchAddressDetail())
                .set(enterprise.introduction, request.getIntroduction())
                .set(enterprise.employeeCount, request.getEmployeeCount())
                .set(enterprise.site, request.getSite())
                .set(enterprise.turnover, request.getTurnover())
                .set(enterprise.director.email, request.getDirectorEmail())
                .set(enterprise.director.name, request.getDirectorName())
                .set(enterprise.director.telephoneNumber, request.getDirectorTelephoneNumber())
                .set(enterprise.director.phoneNumber, request.getDirectorPhoneNumber())
                .set(enterprise.director.department, request.getDirectorDepartment())
                .where(enterprise.registrationNumber.eq(registrationNumber))
                .execute();

        queryFactory
                .delete(businessArea)
                .where(businessArea.enterprise.registrationNumber.eq(registrationNumber))
                .execute();
    }
}
