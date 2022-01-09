package com.jobits.dsm.benecia.domain.recruitment.domain;

import com.jobits.dsm.benecia.domain.attachment.domain.Attachment;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
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
