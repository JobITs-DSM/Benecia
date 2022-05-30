package com.jobits.dsm.benecia.domain.student.domain;

import com.jobits.dsm.benecia.domain.student.code.DepartmentCode;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class Department {

    @Id
    @Column(columnDefinition = "CHAR(6)")
    @Convert(converter = DepartmentCode.DepartmentCodeConverter.class)
    private DepartmentCode type;

    @Column(length = 30)
    private String name;

    private String description;

    private String image;

    @Column(length = 30)
    private String teacher;
}
