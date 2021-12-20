package com.jobits.dsm.benecia.domain.enterprise.domain.businessarea;

import com.jobits.dsm.benecia.domain.enterprise.code.BusinessAreaCode;
import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BusinessArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BusinessAreaCode code;

    @ManyToOne
    @JoinColumn(name = "registration_number")
    private Enterprise enterprise;
}
