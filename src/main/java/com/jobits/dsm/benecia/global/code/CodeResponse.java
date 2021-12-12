package com.jobits.dsm.benecia.global.code;

import lombok.*;

import java.util.List;

@Builder
@Getter
public class CodeResponse {

    private final List<Code> codes;

    @Builder
    @Getter
    public static class Code {

        private final String code;

        private final String value;
    }

    public static Code of(String code, String value) {
        return new Code(code, value);
    }
}
