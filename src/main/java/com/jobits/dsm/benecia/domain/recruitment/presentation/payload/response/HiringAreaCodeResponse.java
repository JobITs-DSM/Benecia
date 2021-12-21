package com.jobits.dsm.benecia.domain.recruitment.presentation.payload.response;

import com.jobits.dsm.benecia.domain.recruitment.code.HiringAreaCode;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class HiringAreaCodeResponse {

    private final List<Code> codes;

    @Builder
    @Getter
    public static class Code {

        private final String code;

        private final String value;

        private final String Category;
    }

    public static Code of(HiringAreaCode hiringArea) {
        return new Code(hiringArea.name(), hiringArea.getValue(), hiringArea.getCategory());
    }
}
