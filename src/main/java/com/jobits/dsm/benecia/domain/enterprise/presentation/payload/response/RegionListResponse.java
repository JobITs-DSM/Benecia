package com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RegionListResponse {
    private final List<RegionInfo> regions;

    @Getter
    @AllArgsConstructor
    public static class RegionInfo {
        private final Integer id;
        private final String name;
    }
}
