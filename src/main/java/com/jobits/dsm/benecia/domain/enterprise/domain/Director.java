package com.jobits.dsm.benecia.domain.enterprise.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
public class Director {

    @NotNull
    @Email
    @Size(max = 320)
    @Column(length = 320)
    private String email;

    @NotNull
    @Size(max = 30)
    @Column(length = 30)
    private String name;

    @NotNull
    @Size(min = 14, max = 14)
    @Column(length = 14)
    private String telephoneNumber;

    @NotNull
    @Size(min = 13, max = 14)
    @Column(length = 13)
    private String phoneNumber;

    @NotNull
    @Size(max = 255)
    private String department;
}
