package com.jobits.dsm.benecia.domain.enterprise.service;

import com.jobits.dsm.benecia.domain.enterprise.domain.region.RegionRepository;
import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.RegionListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

    public RegionListResponse getRegionList(String keyword) {
        return RegionListResponse.builder()
                .regions(regionRepository.findAllByNameContains(keyword)
                        .stream().map(regions -> new RegionListResponse.RegionInfo(regions.getId(), regions.getName()))
                        .collect(Collectors.toList())
                ).build();
    }
}
