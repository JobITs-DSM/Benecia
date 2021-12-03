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
    private String branch_postal_code;

    @NotNull
    @Size(max = 255)
    private String branch_address;

    @NotNull
    @Size(max = 255)
    private String branch_address_detail;
}
