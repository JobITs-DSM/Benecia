package com.jobits.dsm.benecia.domain.recruitment.domain.tag;

import com.jobits.dsm.benecia.domain.recruitment.domain.Recruitment;
import org.springframework.data.repository.CrudRepository;

public interface RecruitmentTagRepository extends CrudRepository<RecruitmentTag, RecruitmentTagId> {
    void deleteAllByRecruitment(Recruitment recruitment);
}
