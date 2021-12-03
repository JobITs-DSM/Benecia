package com.jobits.dsm.benecia.domain.enterprise.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Director {
    @NotNull
    @Size(max = 320)
    private String director_email;

    @NotNull
    @Size(max = 30)
    private String director_name;

    @NotNull
    @Size(min = 14, max = 14)
    private String director_telephone_number;

    @NotNull
    @Size(min = 13, max = 14)
    private String director_phone_number;

    @NotNull
    @Size(max = 255)
    private String director_department;
}
