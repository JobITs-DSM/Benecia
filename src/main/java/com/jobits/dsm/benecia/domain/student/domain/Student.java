package com.jobits.dsm.benecia.domain.student.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class Student {

    @Id
    @Size(min = 7, max = 7)
    private String serialNumber;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Size(max = 320)
    private String email;

    @NotNull
    @Size(min = 4, max = 4)
    private String studentNumber;

    @NotBlank
    @Size(max = 60)
    private String password;

}
