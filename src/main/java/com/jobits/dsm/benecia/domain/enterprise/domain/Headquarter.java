package com.jobits.dsm.benecia.domain.enterprise.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Headquarter {

    @NotNull
    @Size(min = 5, max = 5)
    private String postal_code;

    @NotNull
    @Size(max = 255)
    private String address;

    @NotNull
    @Size(max = 255)
    private String address_detail;
}
