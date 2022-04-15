package com.jobits.dsm.benecia.domain.student.domain;

import com.jobits.dsm.benecia.domain.student.domain.vo.StudentCurrentStatusVO;

public interface StudentRepositoryCustom {
    StudentCurrentStatusVO queryStudentCurrentStatus(String serialNumber);
}
