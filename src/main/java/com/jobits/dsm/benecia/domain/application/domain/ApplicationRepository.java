package com.jobits.dsm.benecia.domain.application.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer>, ApplicationRepositoryCustom {
    @Query("SELECT a FROM Application a WHERE a.recruitment.recruitmentId.registrationNumber = :registrationNumber AND a.recruitment.recruitmentId.receptionYear = :receptionYear")
    List<Application> findAllByRecruitment(String registrationNumber, String receptionYear);
}
