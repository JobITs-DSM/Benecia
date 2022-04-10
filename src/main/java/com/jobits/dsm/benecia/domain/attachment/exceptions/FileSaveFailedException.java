package com.jobits.dsm.benecia.domain.attachment.exceptions;

import com.jobits.dsm.benecia.global.error.exception.GlobalException;

public class FileSaveFailedException extends GlobalException {

    public static final GlobalException EXCEPTION = new FileSaveFailedException();

    private FileSaveFailedException() {
        super(AttachmentErrorCode.FILE_SAVE_FAILED);
    }
}
