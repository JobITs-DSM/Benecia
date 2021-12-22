package com.jobits.dsm.benecia.domain.enterprise.presentation.payload.request;

import com.jobits.dsm.benecia.domain.enterprise.code.BusinessAreaCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseEmployeeCountCode;
import com.jobits.dsm.benecia.domain.enterprise.domain.Address;
import com.jobits.dsm.benecia.domain.enterprise.domain.Director;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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

    private Address address;

    private Address branch;

    @NotNull
    private String introduction;

    private EnterpriseEmployeeCountCode employeeCount;

    @NotNull
    @Size(max = 255)
    private String site;

    @NotNull
    private Integer turnover;

    private Director director;

    private List<BusinessAreaCode> businessAreas;

    private MultipartFile businessLicense;

    private MultipartFile logo;

    private MultipartFile material;

    private MultipartFile foreground;
}
