package com.jobits.dsm.benecia.domain.attachment.exceptions;

import com.jobits.dsm.benecia.global.error.exception.GlobalException;

public class AttachmentNotFoundException extends GlobalException {

    public static final GlobalException EXCEPTION = new AttachmentNotFoundException();

    private AttachmentNotFoundException() {
        super(AttachmentErrorCode.ATTACHMENT_NOT_FOUND);
    }
}
