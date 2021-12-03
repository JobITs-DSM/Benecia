package com.jobits.dsm.benecia.domain.review.domain;

import com.jobits.dsm.benecia.domain.recruitment.code.ProgrammingLanguageCode;
import com.jobits.dsm.benecia.domain.review.code.ReviewCode;
import lombok.*;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Review {
    @Id
    private Integer id;

    @NotNull
    @Size(min = 4, max = 4)
    private String training_year;

    @NotNull
    @Size(max = 255)
    private String content;

    @Convert(converter = ReviewCode.ReviewCodeConverter.class)
    private ReviewCode division;

    @NotNull
    private LocalDateTime registration_date_time;
}
