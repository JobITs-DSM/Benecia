package com.jobits.dsm.benecia.domain.enterprise.domain;

import com.jobits.dsm.benecia.domain.attachment.domain.Attachment;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseDivisionCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseEmployeeCountCode;
import com.jobits.dsm.benecia.domain.enterprise.domain.businessarea.BusinessArea;
import com.jobits.dsm.benecia.domain.review.domain.Review;
import com.jobits.dsm.benecia.global.able.Savable;
import com.jobits.dsm.benecia.global.security.auth.UserMarker;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
public class Enterprise implements UserMarker, Savable {

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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "email", column = @Column(name = "director_email", length = 320)),
            @AttributeOverride(name = "name", column = @Column(name = "director_name", length = 30)),
            @AttributeOverride(name = "telephoneNumber", column = @Column(name = "director_telephone_number", length = 14)),
            @AttributeOverride(name = "phoneNumber", column = @Column(name = "director_phone_number", length = 13)),
            @AttributeOverride(name = "department", column = @Column(name = "director_department"))
    })
    private Director director;

    private Integer lastReceptionYear;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "businessLicense")
    private Attachment businessLicense;

    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "logo")
    private Attachment logo;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material")
    private Attachment material;

    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "foreground")
    private Attachment foreground;

    @OneToMany(mappedBy = "enterprise", orphanRemoval = true)
    private final List<BusinessArea> businessAreas = new ArrayList<>();

    @OneToMany(mappedBy = "enterprise", orphanRemoval = true)
    private List<Review> reviews;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region")
    private Region region;

    @Override
    public String getDirectoryName() {
        return "enterprise";
    }

    @Override
    public String getId() {
        return registrationNumber;
    }

    @Override
    public String getPassword() {
        return registrationNumber;
    }
}
