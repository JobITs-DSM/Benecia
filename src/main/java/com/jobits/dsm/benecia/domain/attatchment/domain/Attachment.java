package com.jobits.dsm.benecia.domain.attatchment.domain;

import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 255)
    private String fileName;

    @NotNull
    @Size(max = 100)
    private String originalFileName;
}
