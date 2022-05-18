package com.jobits.dsm.benecia.domain.student.service;

import com.jobits.dsm.benecia.domain.student.domain.Department;
import com.jobits.dsm.benecia.domain.student.domain.DepartmentRepository;
import com.jobits.dsm.benecia.domain.student.domain.Student;
import com.jobits.dsm.benecia.domain.student.domain.StudentRepository;
import com.jobits.dsm.benecia.domain.student.domain.vo.StudentCurrentStatusVO;
import com.jobits.dsm.benecia.domain.student.exceptions.DepartmentNotFoundException;
import com.jobits.dsm.benecia.domain.student.facade.StudentFacade;
import com.jobits.dsm.benecia.domain.student.presentation.payload.response.DepartmentInformationListResponse;
import com.jobits.dsm.benecia.domain.student.presentation.payload.response.DepartmentStudentListResponse;
import com.jobits.dsm.benecia.domain.student.presentation.payload.response.StudentCurrentStatusResponse;
import com.jobits.dsm.benecia.domain.student.presentation.payload.response.StudentEmploymentRateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final StudentFacade studentFacade;

    public StudentEmploymentRateResponse getEmploymentRate() {

        Integer countOfStudent = studentRepository.countAllBySerialNumberBetween(Student.getFirstSerialNumber(), Student.getLastSerialNumber());
        Integer countOfFoundJobStudent = studentRepository.countAllByIsFoundJobIsTrueAndSerialNumberBetween(Student.getFirstSerialNumber(), Student.getLastSerialNumber());
        
        Float employmentRate = countOfFoundJobStudent.floatValue() / countOfStudent * 100;

        return StudentEmploymentRateResponse.builder()
                .percent(employmentRate)
                .build();
    }

    public List<DepartmentInformationListResponse> getDepartmentInformationList() {
        List<Department> departments = departmentRepository.findAll();

        return departments.stream().map(department -> {
            Integer countOfStudent = studentRepository.countAllBySerialNumberBetweenAndDepartment(Student.getFirstSerialNumber(), Student.getLastSerialNumber(), department);
            Integer countOfFoundJobStudent = studentRepository.countAllByIsFoundJobIsTrueAndSerialNumberBetweenAndDepartment(Student.getFirstSerialNumber(), Student.getLastSerialNumber(), department);

            // 분모 0 처리
            Float employmentRate = countOfStudent == 0 ? 0 : countOfFoundJobStudent.floatValue() / countOfStudent * 100;

            return DepartmentInformationListResponse.builder()
                    .id(department.getId())
                    .image(department.getImage())
                    .name(department.getName())
                    .percent(employmentRate)
                    .studentCount(countOfStudent)
                    .foundJobStudentCount(countOfFoundJobStudent)
                    .teacherName(department.getTeacher())
                    .description(department.getDescription())
                    .build();
        }).collect(Collectors.toList());
    }

    public List<DepartmentStudentListResponse> getDepartmentStudentList(Integer departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> DepartmentNotFoundException.EXCEPTION);

        List<Student> students = studentRepository.findAllByDepartment(department);

        return students.stream()
                .map(student -> DepartmentStudentListResponse.builder()
                    .name(student.getName())
                    .isFoundJob(student.getIsFoundJob())
                    .studentNumber(student.getStudentNumber())
                    .build())
                .collect(Collectors.toList());
    }

    public StudentCurrentStatusResponse queryStudentCurrentStatus() {
        Student student = studentFacade.getCurrentUser();
        StudentCurrentStatusVO studentStatus = studentRepository.queryStudentCurrentStatus(student.getSerialNumber());
        return StudentCurrentStatusResponse.builder()
                .userName(studentStatus.getUserName())
                .userProfileImageUrl(studentStatus.getUserProfileImageUrl())
                .employmentStatus(studentStatus.getEmploymentStatus().getCode())
                .enterpriseImageUrl(studentStatus.getEnterpriseImageUrl())
                .field(studentStatus.getField().getValue())
                .build();
    }
}
