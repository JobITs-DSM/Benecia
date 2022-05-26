package com.jobits.dsm.benecia.domain.recruitment.domain.welfare;

import com.jobits.dsm.benecia.domain.recruitment.code.WelfareCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WelfareRepository extends JpaRepository<Welfare, Integer> {
    @Query("SELECT w.code FROM Welfare w WHERE w.recruitment.recruitmentId.receptionYear = :receptionYear and w.recruitment.recruitmentId.registrationNumber = :registrationNumber")
    List<WelfareCode> findAllByRecruitment(String receptionYear, String registrationNumber);
}
