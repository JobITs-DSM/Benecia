package com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TagListResponse {
    private final List<TagInfo> tags;

    @Getter
    @AllArgsConstructor
    public static class TagInfo {
        private final Integer id;
        private final String name;
    }
}
