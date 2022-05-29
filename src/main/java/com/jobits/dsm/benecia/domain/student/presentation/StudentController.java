package com.jobits.dsm.benecia.domain.student.presentation;

import com.jobits.dsm.benecia.domain.student.code.DepartmentCode;
import com.jobits.dsm.benecia.domain.student.presentation.payload.response.DepartmentInformationListResponse;
import com.jobits.dsm.benecia.domain.student.presentation.payload.response.DepartmentStudentListResponse;
import com.jobits.dsm.benecia.domain.student.presentation.payload.response.StudentCurrentStatusResponse;
import com.jobits.dsm.benecia.domain.student.presentation.payload.response.StudentEmploymentRateResponse;
import com.jobits.dsm.benecia.domain.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/department/{department-code}")
    public List<DepartmentStudentListResponse> departmentStudents(@PathVariable("department-code") DepartmentCode departmentCode) {
        return studentService.getDepartmentStudentList(departmentCode);
    }

    @GetMapping("/status")
    public StudentCurrentStatusResponse queryStudentCurrentStatus() {
        return studentService.queryStudentCurrentStatus();
    }

    @PatchMapping("/{student-id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchStatus(@PathVariable("student-id") String studentId) {
        studentService.patchStudentStatus(studentId, true);
    }

    @DeleteMapping("/{student-id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchFalseStatus(@PathVariable("student-id") String studentId) {
        studentService.patchStudentStatus(studentId, false);
    }
}
