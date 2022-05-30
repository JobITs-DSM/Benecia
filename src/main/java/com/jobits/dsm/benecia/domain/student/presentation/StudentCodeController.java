package com.jobits.dsm.benecia.domain.student.presentation;

import com.jobits.dsm.benecia.domain.student.service.StudentCodeService;
import com.jobits.dsm.benecia.global.code.CodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student/codes")
public class StudentCodeController {

    private final StudentCodeService departmentCodeService;

    @GetMapping("/department")
    public CodeResponse department() {
        return departmentCodeService.getDepartmentCodes();
    }
}
