package com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class QueryApplicantListResponse {

    private final List<ApplicationInfo> studentApplications;

    @Getter
    @Builder
    public static class ApplicationInfo {
        private final String studentNumber;
        private final String studentName;
        private final LocalDateTime dateTime;
        private final List<AttachmentInfo> attachments;
    }

    @Getter
    @Builder
    public static class AttachmentInfo {
        private final String name;
        private final String fileUrl;
    }
}
