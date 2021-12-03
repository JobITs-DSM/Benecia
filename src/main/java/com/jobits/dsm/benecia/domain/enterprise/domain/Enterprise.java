package com.jobits.dsm.benecia.domain.enterprise.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
public class Enterprise {
    @Id
    @Size(min = 12, max = 12)
    private String registration_number;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Size(min = 4, max = 4)
    private String establish_year;

    @NotNull
    private Boolean is_convention;

    @NotNull
    @Size(min = 6, max = 6)
    private String division;

    @NotNull
    @Size(max = 30)
    private String representative_name;

    @NotNull
    @Size(min = 5, max = 5)
    private String postal_code;

    @NotNull
    @Size(max = 255)
    private String address;

    @NotNull
    @Size(max = 255)
    private String address_detail;

    @NotNull
    @Size(min = 5, max = 5)
    private String branch_postal_code;

    @NotNull
    @Size(max = 255)
    private String branch_address;

    @NotNull
    @Size(max = 255)
    private String branch_address_detail;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String introduction;

    @NotNull
    @Size(min = 6, max = 6)
    private String employee_count;

    @NotNull
    @Size(max = 255)
    private String site;

    @NotNull
    private Integer turnover;

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
