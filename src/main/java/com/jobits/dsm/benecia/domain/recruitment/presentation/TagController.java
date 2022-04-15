package com.jobits.dsm.benecia.domain.recruitment.presentation;

import com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response.TagListResponse;
import com.jobits.dsm.benecia.domain.recruitment.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    @GetMapping
    public TagListResponse getTagList(@RequestParam(required = false) String keyword) {
        return keyword != null ? tagService.getTagList(keyword) : TagListResponse.builder().build();
    }
}
