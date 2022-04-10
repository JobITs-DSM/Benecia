package com.jobits.dsm.benecia.domain.attachment.service;

import com.jobits.dsm.benecia.domain.attachment.domain.Attachment;
import com.jobits.dsm.benecia.domain.attachment.facade.AttachmentFacade;
import com.jobits.dsm.benecia.domain.attachment.presentation.payload.response.SaveAttachmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class AttachmentService {

    private final AttachmentFacade attachmentFacade;

    public SaveAttachmentResponse saveAttachment(MultipartFile file) {
        Attachment attachment = attachmentFacade.saveAttachment(file);

        return SaveAttachmentResponse.builder()
                .id(attachment.getId())
                .fileName(attachment.getFileName())
                .originalFileName(attachment.getOriginalFileName())
                .build();
    }
}
