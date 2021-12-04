package com.jobits.dsm.benecia.domain.enterprise.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Director {

    @NotNull
    @Email
    @Size(max = 320)
    private String directorEmail;

    @NotNull
    @Size(max = 30)
    private String directorName;

    @NotNull
    @Size(min = 14, max = 14)
    private String directorTelephoneNumber;

    @NotNull
    @Size(min = 13, max = 14)
    private String directorPhoneNumber;

    @NotNull
    @Size(max = 255)
    private String directorDepartment;
}
