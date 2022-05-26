package com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea;

import com.jobits.dsm.benecia.domain.recruitment.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HiringAreaRepository extends JpaRepository<HiringArea, Integer>{
    void deleteAllByRecruitment(Recruitment recruitment);
}
