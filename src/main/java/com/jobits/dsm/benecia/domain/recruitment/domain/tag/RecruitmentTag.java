package com.jobits.dsm.benecia.domain.recruitment.domain.tag;

import com.jobits.dsm.benecia.domain.recruitment.domain.Recruitment;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RecruitmentTag {
    @EmbeddedId
    private RecruitmentTagId recruitmentTagId;

    @MapsId("tagId")
    @ManyToOne
    @JoinColumn
    private Tag tag;

    @MapsId("recruitmentId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "reception_year"),
            @JoinColumn(name = "registration_number")
    })
    private Recruitment recruitment;
}
