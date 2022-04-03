package com.jobits.dsm.benecia.domain.student.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Integer countAllBySerialNumberBetween(String start, String end);
    Integer countAllByIsFoundJobIsTrueAndSerialNumberBetween(String start, String end);
}
