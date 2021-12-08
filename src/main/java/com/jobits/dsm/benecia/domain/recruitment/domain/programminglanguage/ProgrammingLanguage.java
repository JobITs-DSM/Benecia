package com.jobits.dsm.benecia.domain.recruitment.domain.programminglanguage;

import com.jobits.dsm.benecia.domain.recruitment.code.ProgrammingLanguageCode;
import com.jobits.dsm.benecia.domain.recruitment.domain.Recruitment;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProgrammingLanguage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = ProgrammingLanguageCode.ProgrammingLanguageCodeConverter.class)
    private ProgrammingLanguageCode code;

    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;
}
