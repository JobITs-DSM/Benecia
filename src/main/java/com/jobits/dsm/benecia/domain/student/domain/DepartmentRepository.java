package com.jobits.dsm.benecia.domain.student.domain;

import com.jobits.dsm.benecia.domain.student.code.DepartmentCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, DepartmentCode> {
}
