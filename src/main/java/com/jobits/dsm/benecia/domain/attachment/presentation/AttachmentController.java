package com.jobits.dsm.benecia.domain.attachment.presentation;

import com.jobits.dsm.benecia.domain.attachment.presentation.payload.response.SaveAttachmentResponse;
import com.jobits.dsm.benecia.domain.attachment.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SaveAttachmentResponse attachment(@RequestParam("file")MultipartFile file) {
        return attachmentService.saveAttachment(file);
    }
}
