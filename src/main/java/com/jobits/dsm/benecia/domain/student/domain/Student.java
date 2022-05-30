package com.jobits.dsm.benecia.domain.student.domain;

import com.jobits.dsm.benecia.domain.application.domain.Application;
import com.jobits.dsm.benecia.domain.attachment.domain.Attachment;
import com.jobits.dsm.benecia.global.security.auth.UserMarker;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class Student implements UserMarker {

    @Id
    @Size(min = 7, max = 7)
    private String serialNumber;

    @NotNull
    @Size(max = 30)
    private String name;

    @Setter
    @NotNull
    @Size(max = 320)
    private String email;

    @NotNull
    @Size(min = 4, max = 4)
    private String studentNumber;

    @NotBlank
    @Size(max = 60)
    private String password;

    @NotNull
    private Boolean isFoundJob;

    @OneToMany(mappedBy = "studentSerialNumber", orphanRemoval = true)
    private List<Application> applications;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_type")
    private Department department;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_image")
    private Attachment profileImage;

    @Override
    public String getId() {
        return serialNumber;
    }

    public static String getFirstSerialNumber() {
        return LocalDate.now().minusYears(2).getYear() + "001";
    }

    public static String getLastSerialNumber() {
        return LocalDate.now().minusYears(2).getYear() + "100";
    }

    public void setIsFoundJob(Boolean foundJob) {
        isFoundJob = foundJob;
    }
}
