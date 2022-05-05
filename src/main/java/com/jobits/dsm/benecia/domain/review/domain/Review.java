package com.jobits.dsm.benecia.domain.review.domain;

import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import com.jobits.dsm.benecia.domain.review.code.ReviewCode;
import com.jobits.dsm.benecia.domain.student.domain.Student;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 4, max = 4)
    private String trainingYear;

    @NotNull
    @Size(max = 255)
    private String content;

    @Convert(converter = ReviewCode.ReviewCodeConverter.class)
    private ReviewCode division;

    @NotNull
    private LocalDateTime registrationDateTime;

    @NotNull
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean isConfirmed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registration_number")
    private Enterprise enterprise;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_serial_number")
    private Student student;

    public void updateIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }
}
