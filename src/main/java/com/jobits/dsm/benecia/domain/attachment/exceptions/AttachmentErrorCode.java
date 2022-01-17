package com.jobits.dsm.benecia.domain.attachment.exceptions;

import com.jobits.dsm.benecia.global.error.ErrorAttribute;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AttachmentErrorCode implements ErrorAttribute {

    FILE_SAVE_FAILED(400, "File Save Failed");

    private final int status;
    private final String message;
}
