package com.jobits.dsm.benecia.domain.attachment.presentation.payload.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SaveAttachmentResponse {

    private final Integer id;

    private final String fileName;

    private final String originalFileName;
}
