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
public class Director {

    @NotNull
    @Email
    @Size(max = 320)
    @Column(length = 320)
    @JsonProperty("email")
    private String directorEmail;

    @NotNull
    @Size(max = 30)
    @Column(length = 30)
    @JsonProperty("name")
    private String directorName;

    @NotNull
    @Size(min = 14, max = 14)
    @Column(length = 14)
    @JsonProperty("telephone_number")
    private String directorTelephoneNumber;

    @NotNull
    @Size(min = 13, max = 14)
    @Column(length = 13)
    @JsonProperty("phone_number")
    private String directorPhoneNumber;

    @NotNull
    @Size(max = 255)
    @JsonProperty("department")
    private String directorDepartment;
}
