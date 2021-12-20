package com.jobits.dsm.benecia.domain.recruitment.domain.technology;

import com.jobits.dsm.benecia.domain.recruitment.code.TechnologyCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.Recruitment;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Convert(converter = TechnologyCode.TechnologyCodeConverter.class)
    private TechnologyCode code;

    @ManyToOne
    @JoinColumn(name = "reception_year")
    @JoinColumn(name = "registration_number")
    private Recruitment recruitment;
}
