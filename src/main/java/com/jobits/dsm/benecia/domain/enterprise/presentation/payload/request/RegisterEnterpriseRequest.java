package com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request;

import com.jobits.dsm.benecia.domain.enterprise.code.BusinessAreaCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseEmployeeCountCode;
import com.jobits.dsm.benecia.domain.enterprise.domain.Director;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterEnterpriseRequest {

    @Size(min = 12, max = 12)
    private String registrationNumber;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Size(min = 4, max = 4)
    private String establishYear;

    @NotNull
    @Size(max = 30)
    private String representativeName;

    private String address;

    @NotNull
    private String introduction;

    @NotNull
    private EnterpriseEmployeeCountCode employeeCount;

    @Size(max = 255)
    private String site;

    private Integer turnover;

    private Director director;

    private BusinessAreaCode businessArea;

    @NotNull
    private Integer region;

    @NotNull
    private Integer businessLicense;

    private Integer logo;

    private Integer material;

    private Integer foreground;
}
