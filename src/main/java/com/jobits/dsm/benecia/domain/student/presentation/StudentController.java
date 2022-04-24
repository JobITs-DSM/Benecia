package com.jobits.dsm.benecia.domain.student.presentation;

import com.jobits.dsm.benecia.domain.student.presentation.payload.response.DepartmentInformationListResponse;
import com.jobits.dsm.benecia.domain.student.presentation.payload.response.StudentCurrentStatusResponse;
import com.jobits.dsm.benecia.domain.student.presentation.payload.response.StudentEmploymentRateResponse;
import com.jobits.dsm.benecia.domain.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/employment")
    public StudentEmploymentRateResponse employmentRate() {
        return studentService.getEmploymentRate();
    }

    @GetMapping("/department")
    public List<DepartmentInformationListResponse> departmentInformation() {
        return studentService.getDepartmentInformationList();
    }

    @GetMapping("/status")
    public StudentCurrentStatusResponse queryStudentCurrentStatus() {
        return studentService.queryStudentCurrentStatus();
    }
}
