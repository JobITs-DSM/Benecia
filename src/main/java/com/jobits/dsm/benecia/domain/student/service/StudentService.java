package com.jobits.dsm.benecia.domain.student.service;

import com.jobits.dsm.benecia.domain.student.domain.Student;
import com.jobits.dsm.benecia.domain.student.domain.StudentRepository;
import com.jobits.dsm.benecia.domain.student.presentation.payload.StudentEmploymentRateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentEmploymentRateResponse getEmploymentRate() {

        Integer countOfStudent = studentRepository.countAllBySerialNumberBetween(Student.getFirstSerialNumber(), Student.getLastSerialNumber());
        Integer countOfFoundJobStudent = studentRepository.countAllByIsFoundJobIsTrueAndSerialNumberBetween(Student.getFirstSerialNumber(), Student.getLastSerialNumber());
        
        Float employmentResponse = countOfFoundJobStudent.floatValue() / countOfStudent * 100;

        return StudentEmploymentRateResponse.builder()
                .percent(employmentResponse)
                .build();
    }
}
