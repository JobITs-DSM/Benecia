package com.jobits.dsm.benecia.domain.student.domain;

import com.jobits.dsm.benecia.domain.student.domain.vo.SearchStudentListVO;
import com.jobits.dsm.benecia.domain.student.domain.vo.StudentCurrentStatusVO;

import java.util.List;

public interface StudentRepositoryCustom {
    StudentCurrentStatusVO queryStudentCurrentStatus(String serialNumber);
    List<SearchStudentListVO> searchStudentList(String name);
}
