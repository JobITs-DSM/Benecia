package com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request;

import com.jobits.dsm.benecia.domain.enterprise.code.BusinessAreaCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseEmployeeCountCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyEnterpriseInfoRequest {
    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(min = 4, max = 4)
    private String establishYear;

    @NotBlank
    @Size(max = 30)
    private String representativeName;

    @NotBlank
    @Size(min = 5, max = 5)
    private String postalCode;

    @NotBlank
    @Size(max = 255)
    private String address;

    @Size(max = 255)
    private String addressDetail;

    @Size(min = 5, max = 5)
    private String branchPostalCode;

    @Size(max = 255)
    private String branchAddress;

    @Size(max = 255)
    private String branchAddressDetail;

    @NotNull
    private String introduction;

    @NotNull
    private EnterpriseEmployeeCountCode employeeCount;

    @Size(max = 255)
    private String site;

    private Integer turnover;

    @NotNull
    @Size(max = 320)
    private String directorEmail;

    @NotNull
    @Size(max = 30)
    private String directorName;

    @NotNull
    @Size(min = 14, max = 14)
    private String directorTelephoneNumber;

    @NotNull
    @Size(min = 13, max = 13)
    private String directorPhoneNumber;

    @NotNull
    @Size(max = 255)
    private String directorDepartment;

    @NotNull
    private List<BusinessAreaCode> businessAreas;
}
