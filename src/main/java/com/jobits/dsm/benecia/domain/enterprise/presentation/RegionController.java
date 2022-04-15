package com.jobits.dsm.benecia.domain.enterprise.presentation;

import com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response.RegionListResponse;
import com.jobits.dsm.benecia.domain.enterprise.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/region")
public class RegionController {
    private final RegionService regionService;

    @GetMapping
    public RegionListResponse getRegionList(@RequestParam(required = false) String keyword) {
        return keyword != null ? regionService.getRegionList(keyword) : RegionListResponse.builder().build();
    }
}
