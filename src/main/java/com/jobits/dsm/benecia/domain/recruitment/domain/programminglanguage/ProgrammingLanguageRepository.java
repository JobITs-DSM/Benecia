package com.jobits.dsm.benecia.domain.recruitment.domain.programminglanguage;

import com.jobits.dsm.benecia.domain.recruitment.code.ProgrammingLanguageCode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProgrammingLanguageRepository extends CrudRepository<ProgrammingLanguage, Long> {
    @Query("SELECT pl.code FROM ProgrammingLanguage pl WHERE pl.recruitment.recruitmentId.receptionYear = :receptionYear and pl.recruitment.recruitmentId.registrationNumber = :registrationNumber")
    List<ProgrammingLanguageCode> findAllByRecruitment(String receptionYear, String registrationNumber);
}
