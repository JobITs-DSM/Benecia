package com.jobits.dsm.benecia.domain.recruitment.service;

import com.jobits.dsm.benecia.domain.recruitment.domain.tag.TagRepository;
import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response.TagListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public TagListResponse getTagList(String keyword) {
        return TagListResponse.builder()
                .tags(tagRepository.findByNameContains(keyword))
                .build();
    }
}
