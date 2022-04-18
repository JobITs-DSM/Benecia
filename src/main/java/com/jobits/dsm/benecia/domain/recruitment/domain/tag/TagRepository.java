package com.jobits.dsm.benecia.domain.recruitment.domain.tag;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Integer> {
    List<Tag> findAllByNameContains(String keyword);
    @Query("SELECT t.name FROM Tag t inner join RecruitmentTag rt on rt.tag.id = t.id " +
            "WHERE rt.recruitment.recruitmentId.receptionYear = :receptionYear and rt.recruitment.recruitmentId.registrationNumber = :registrationNumber ")
    List<String> findAllByRecruitment(String receptionYear, String registrationNumber);
}
