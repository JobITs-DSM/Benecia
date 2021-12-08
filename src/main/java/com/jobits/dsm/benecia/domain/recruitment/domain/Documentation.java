package com.jobits.dsm.benecia.domain.recruitment.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Documentation {

    @Size(max = 30)
    private String documentation1;

    @Size(max = 30)
    private String documentation2;

    @Size(max = 30)
    private String documentation3;

}
