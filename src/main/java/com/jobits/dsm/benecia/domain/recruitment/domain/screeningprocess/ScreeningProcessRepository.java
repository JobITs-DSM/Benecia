package com.jobits.dsm.benecia.domain.recruitment.domain.screeningprocess;

import com.jobits.dsm.benecia.domain.recruitment.code.ScreeningProcessCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScreeningProcessRepository extends JpaRepository<ScreeningProcess, Integer> {
    @Query("SELECT sp.code FROM ScreeningProcess sp WHERE sp.recruitment.recruitmentId.receptionYear = :receptionYear and sp.recruitment.recruitmentId.registrationNumber = :registrationNumber")
    List<ScreeningProcessCode> findAllByRecruitment(String receptionYear, String registrationNumber);
    void deleteAllByRecruitment(Recruitment recruitment);
}
