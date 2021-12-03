package com.jobits.dsm.benecia.domain.contract.domain;

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
public class Contract {
    @Id
    private Integer id;

    @NotNull
    private LocalDateTime date_time;
}
