package com.jobits.dsm.benecia.domain.student.presentation;

import com.jobits.dsm.benecia.domain.student.presentation.payload.StudentEmploymentRateResponse;
import com.jobits.dsm.benecia.domain.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/employment")
    public StudentEmploymentRateResponse employmentRate() {
        return studentService.getEmploymentRate();
    }
}
