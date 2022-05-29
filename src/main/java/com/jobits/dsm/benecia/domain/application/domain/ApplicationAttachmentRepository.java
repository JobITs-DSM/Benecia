package com.jobits.dsm.benecia.domain.application.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationAttachmentRepository extends JpaRepository<ApplicationAttachment, Integer> {
    List<ApplicationAttachment> findAllByApplicationId(Integer applicationId);
}
