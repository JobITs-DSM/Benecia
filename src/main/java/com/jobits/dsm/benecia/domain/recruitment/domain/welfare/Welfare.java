package com.jobits.dsm.benecia.domain.recruitment.domain.welfare;

import com.jobits.dsm.benecia.domain.recruitment.code.WelfareCode;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Welfare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Convert(converter = WelfareCode.WelfareCodeConverter.class)
    private WelfareCode code;
}
