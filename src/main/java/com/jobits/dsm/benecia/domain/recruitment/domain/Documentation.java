package com.jobits.dsm.benecia.domain.recruitment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Embeddable
public class Documentation {

    @Column(length = 30)
    private String documentation1;

    @Column(length = 30)
    private String documentation2;

    @Column(length = 30)
    private String documentation3;

}
