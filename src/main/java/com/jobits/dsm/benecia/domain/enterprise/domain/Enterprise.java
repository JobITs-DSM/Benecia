package com.jobits.dsm.benecia.domain.enterprise.domain;

import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseDivisionCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseEmployeeCountCode;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
public class Enterprise {

    @Id
    @Size(min = 12, max = 12)
    private String registrationNumber;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Size(min = 4, max = 4)
    private String establishYear;

    @NotNull
    private Boolean isConvention;

    @Convert(converter = EnterpriseDivisionCode.EnterpriseDivisionCodeConverter.class)
    private EnterpriseDivisionCode division;

    @NotNull
    @Size(max = 30)
    private String representativeName;

    @Embedded
    private Address address;

    @Embedded
    private Address branchAddress;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Convert(converter = EnterpriseEmployeeCountCode.EnterpriseEmployeeCountCodeConverter.class)
    private EnterpriseEmployeeCountCode employeeCount;

    @NotNull
    @Size(max = 255)
    private String site;

    @NotNull
    private Integer turnover;

    @Embedded
    private Director director;
}
