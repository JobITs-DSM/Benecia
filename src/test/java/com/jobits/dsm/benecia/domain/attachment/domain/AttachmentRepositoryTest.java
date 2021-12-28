package com.jobits.dsm.benecia.domain.attachment.domain;

import com.jobits.dsm.benecia.domain.attatchment.domain.Attachment;
import com.jobits.dsm.benecia.domain.attatchment.domain.AttachmentRepository;
import com.jobits.dsm.benecia.infrastructure.querydsl.QuerydslConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Import(QuerydslConfig.class)
@Rollback(false)
public class AttachmentRepositoryTest {
    @Autowired
    private AttachmentRepository attachmentRepository;

    @BeforeEach
    void cleanUp() {
        attachmentRepository.deleteAll();
    }

    @Test
    void 저장_성공_테스트() {
        Attachment attachment = Attachment.builder()
                .fileName("filename")
                .originalFileName("originalFilename")
                .build();
        assertThat(attachmentRepository.save(attachment).getFileName()).isEqualTo("filename");
    }

}
