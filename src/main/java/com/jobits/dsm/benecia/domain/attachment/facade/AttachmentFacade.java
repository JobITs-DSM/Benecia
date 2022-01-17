package com.jobits.dsm.benecia.domain.attachment.facade;

import com.jobits.dsm.benecia.domain.attachment.domain.Attachment;
import com.jobits.dsm.benecia.domain.attachment.domain.AttachmentRepository;
import com.jobits.dsm.benecia.domain.attachment.exceptions.FileSaveFailedException;
import com.jobits.dsm.benecia.global.able.Savable;
import com.jobits.dsm.benecia.infrastructure.s3.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Component
public class AttachmentFacade {

    private final S3Util s3Util;

    private final AttachmentRepository attachmentRepository;

    public Attachment saveAttachment(MultipartFile file) {
        Optional<String> savedFile = saveFileToStorage(file, "attachment");

        if (savedFile.isEmpty()) {
            throw FileSaveFailedException.EXCEPTION;
        }

        return saveFileToDatabase(savedFile.get(), file.getOriginalFilename());
    }

    public <T extends Savable> void saveAttachment(T entity, MultipartFile file, Consumer<Attachment> consumer) {
        Optional<String> savedFile = saveFileToStorage(file, entity.getDirectoryName() + "/" + entity.getId());
        if (savedFile.isPresent()) {
            Attachment attachment = saveFileToDatabase(savedFile.get(), file.getOriginalFilename());
            consumer.accept(attachment);
        }
    }

    private Optional<String> saveFileToStorage(MultipartFile file, String directoryName) {
        if (file == null || file.isEmpty() || file.getOriginalFilename() == null) {
            return Optional.empty();
        }
        return Optional.of(s3Util.saveFile(file, directoryName));
    }

    private Attachment saveFileToDatabase(String fileName, String originalFileName) {
        return attachmentRepository.save(Attachment.builder()
                .fileName(fileName)
                .originalFileName(originalFileName)
                .build());
    }
}
