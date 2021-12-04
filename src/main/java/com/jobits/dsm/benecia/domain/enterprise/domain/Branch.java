package com.jobits.dsm.benecia.domain.enterprise.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Branch {

    @NotNull
    @Size(min = 5, max = 5)
    private String branchPostalCode;

    @NotNull
    @Size(max = 255)
    private String branchAddress;

    @NotNull
    @Size(max = 255)
    private String branchAddressDetail;
}
