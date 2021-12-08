package com.jobits.dsm.benecia.domain.training.domain;

import com.jobits.dsm.benecia.domain.recruitment.domain.Recruitment;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private LocalDateTime beginDateTime;

    private LocalDateTime endDateTime;

    @ManyToOne
    @JoinColumn(name = "reception_year")
    @JoinColumn(name = "registration_number")
    private Recruitment recruitment;

}
