package com.jobits.dsm.benecia.domain.recruitment.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, RecruitmentId> {
}
