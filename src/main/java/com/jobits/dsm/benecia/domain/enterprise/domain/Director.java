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
    private String email;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Size(min = 14, max = 14)
    private String telephoneNumber;

    @NotNull
    @Size(min = 13, max = 14)
    private String phoneNumber;

    @NotNull
    @Size(max = 255)
    private String department;
}
