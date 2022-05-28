package com.jobits.dsm.benecia.domain.application.domain.vo;

import com.jobits.dsm.benecia.domain.attachment.domain.Attachment;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class QueryApplicantListVO {
    private final String studentNumber;
    private final String studentName;
    private final LocalDateTime dateTime;
    private final List<Attachment> attachments;

    @QueryProjection
    public QueryApplicantListVO(String studentNumber, String studentName, LocalDateTime dateTime, List<Attachment> attachments) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.dateTime = dateTime;
        this.attachments = attachments;
    }
}
