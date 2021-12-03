package com.jobits.dsm.benecia.domain.training.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Training {
    @Id
    private Integer id;

    @NotNull
    private LocalDateTime begin_date_time;

    private LocalDateTime end_date_time;
}
