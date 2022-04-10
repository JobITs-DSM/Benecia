package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.attachment.domain.Attachment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Form {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form1")
    private Attachment form1;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form2")
    private Attachment form2;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form3")
    private Attachment form3;
}
