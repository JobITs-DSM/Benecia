package com.jobits.dsm.benecia.domain.recruitment.domain.technology;

import com.jobits.dsm.benecia.domain.recruitment.code.TechnologyCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TechnologyRepository extends JpaRepository<Technology, Integer> {
    @Query("SELECT t.code FROM Technology t WHERE t.recruitment.recruitmentId.receptionYear = :receptionYear and t.recruitment.recruitmentId.registrationNumber = :registrationNumber")
    List<TechnologyCode> findAllByRecruitment(String receptionYear, String registrationNumber);
    void deleteAllByRecruitment(Recruitment recruitment);
}
