package com.jobits.dsm.benecia.domain.student.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    private static final String EMAIL = "email@dsm.hs.kr";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String EXPECTED_STUDENT_NUMBER = "2220";
    private static final String EXPECTED_SERIAL_NUMBER = "2021001";

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();
    }

    @Test
    void 저장_성공_테스트() {

        Student student = Student.builder()
                .email(EMAIL)
                .name(NAME)
                .password(PASSWORD)
                .studentNumber(EXPECTED_STUDENT_NUMBER)
                .serialNumber(EXPECTED_SERIAL_NUMBER)
                .build();

        assertThat(studentRepository.save(student).getSerialNumber()).isEqualTo(EXPECTED_SERIAL_NUMBER);
    }

    @Test
    void 저장_실패_테스트_자릿수() {
        final String invalidSerialNumber = "20210111";        // 자릿수 부족 테스트

        Student student = Student.builder()
                .email(EMAIL)
                .name(NAME)
                .password(PASSWORD)
                .studentNumber(EXPECTED_STUDENT_NUMBER)
                .serialNumber(invalidSerialNumber)
                .build();

        assertThrows(ConstraintViolationException.class, () -> studentRepository.saveAndFlush(student));
    }

    @Test
    void 저장_실패_테스트_NOTNULL() {
        Student student = Student.builder()
                .email(EMAIL)
                .name(NAME)
                .password(null)
                .studentNumber(EXPECTED_STUDENT_NUMBER)
                .serialNumber(EXPECTED_SERIAL_NUMBER)
                .build();

        assertThrows(ConstraintViolationException.class, () -> studentRepository.saveAndFlush(student));
    }

}