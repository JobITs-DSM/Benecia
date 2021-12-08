package com.jobits.dsm.benecia.domain.application.domain;

import com.jobits.dsm.benecia.domain.application.code.ApplicationCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.hiringarea.HiringArea;
import com.jobits.dsm.benecia.domain.student.domain.Student;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Convert(converter = ApplicationCode.ApplicationCodeConverter.class)
    private ApplicationCode status;

    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "CHAR(7)")
    private Student studentSerialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private HiringArea hiringAreaId;
}
