package com.jobits.dsm.benecia.domain.recruitment.domain.tag;

import com.jobits.dsm.benecia.domain.recruitment.domain.RecruitmentId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class RecruitmentTagId implements Serializable {

    private Integer tagId;

    private RecruitmentId recruitmentId;
}
