package com.jobits.dsm.benecia.domain.student.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>, StudentRepositoryCustom {

    Integer countAllBySerialNumberBetween(String start, String end);
    Integer countAllByIsFoundJobIsTrueAndSerialNumberBetween(String start, String end);

    Integer countAllBySerialNumberBetweenAndDepartment(String start, String end, Department department);
    Integer countAllByIsFoundJobIsTrueAndSerialNumberBetweenAndDepartment(String start, String end, Department department);

    @Query("select s from Student s join fetch s.department where s.department = :department")
    List<Student> findAllByDepartment(@Param("department") Department department);
}
