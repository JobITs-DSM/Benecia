package com.jobits.dsm.benecia.domain.enterprise.domain;

import com.jobits.dsm.benecia.domain.attatchment.domain.Attachment;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseDivisionCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseEmployeeCountCode;
import com.jobits.dsm.benecia.domain.enterprise.domain.businessarea.BusinessArea;
import lombok.*;
import lombok.experimental.Delegate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
public class Enterprise {

    @Id
    @Size(min = 12, max = 12)
    @Column(columnDefinition = "CHAR(12)")
    private String registrationNumber;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Size(min = 4, max = 4)
    @Column(columnDefinition = "CHAR(4)")
    private String establishYear;

    @NotNull
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean isConvention;

    @Convert(converter = EnterpriseDivisionCode.EnterpriseDivisionCodeConverter.class)
    @Column(columnDefinition = "CHAR(6)")
    private EnterpriseDivisionCode division;

    @NotNull
    @Size(max = 30)
    private String representativeName;

    @Embedded
    private Address address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "postalCode", column = @Column(name = "branch_postal_code", columnDefinition = "CHAR(5)")),
            @AttributeOverride(name = "address", column = @Column(name = "branch_address")),
            @AttributeOverride(name = "addressDetail", column = @Column(name = "branch_address_detail"))
    })
    private Address branchAddress;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Convert(converter = EnterpriseEmployeeCountCode.EnterpriseEmployeeCountCodeConverter.class)
    @Column(columnDefinition = "CHAR(6)")
    private EnterpriseEmployeeCountCode employeeCount;

    @NotNull
    @Size(max = 255)
    private String site;

    @NotNull
    private Integer turnover;

    @Delegate
    @Embedded
    private Director director;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment businessLicense;

    @OneToOne(fetch = FetchType.EAGER)
    private Attachment logo;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment material;

    @OneToOne(fetch = FetchType.EAGER)
    private Attachment foreground;

    @OneToMany(mappedBy = "enterprise", orphanRemoval = true)
    private List<BusinessArea> businessAreas;
}
