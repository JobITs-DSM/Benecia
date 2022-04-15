package com.jobits.dsm.benecia.domain.student.facade;

import com.jobits.dsm.benecia.domain.student.domain.Student;
import com.jobits.dsm.benecia.domain.student.domain.StudentRepository;
import com.jobits.dsm.benecia.domain.student.exceptions.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StudentFacade {
    private final StudentRepository studentRepository;

    public Student getCurrentUser() {
        String serialNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByAccountId(serialNumber);
    }

    public Student getUserByAccountId(String serialNumber) {
        return studentRepository.findById(serialNumber)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
    }
}
