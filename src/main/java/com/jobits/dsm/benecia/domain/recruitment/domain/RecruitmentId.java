package com.jobits.dsm.benecia.domain.recruitment.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class RecruitmentId implements Serializable {

    @Column(length = 4)
    private String receptionYear;

    @Column(length = 12)
    private String registrationNumber;

}
