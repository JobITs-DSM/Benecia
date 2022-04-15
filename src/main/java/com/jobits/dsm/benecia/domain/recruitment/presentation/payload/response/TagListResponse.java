package com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response;

import com.jobits.dsm.benecia.domain.recruitment.domain.tag.Tag;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TagListResponse {
    private final List<Tag> tags;
}
