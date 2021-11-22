package com.jobits.dsm.benecia.domain.recruitment.code;

import com.jobits.dsm.benecia.domain.recruitment.exception.AttributeConvertFailedException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum ProgrammingLanguageCode {

    C("LANG01", "C"),
    CPP("LANG02", "C++"),
    C_SHARP("LANG03", "C#"),
    JAVA("LANG04", "Java"),
    KOTLIN("LANG05", "Kotlin"),
    HTMLCSS("LANG06", "HTML5/CSS"),
    JAVASCRIPT("LANG07", "JavaScript"),
    TYPESCRIPT("LANG08", "TypeScript"),
    RUBY("LANG09", "Ruby"),
    GO("LANG10", "Go"),
    PYTHON("LANG11", "Python"),
    R("LANG12", "R"),
    OBJECT_C("LANG13", "Object-C"),
    SWIFT("LANG14", "Swift"),
    DART("LANG15", "Dart"),
    RUST("LANG16", "Rust");

    private final String code;
    private final String value;

    private static final Map<String, ProgrammingLanguageCode> map =
            Collections.unmodifiableMap(Arrays.stream(ProgrammingLanguageCode.values())
                    .collect(Collectors.toMap(ProgrammingLanguageCode::getCode, Function.identity())));

    public static ProgrammingLanguageCode find(String dbData) {
        return Optional.of(map.get(dbData))
                .orElseThrow(() -> new IllegalStateException("adf"));
    }

    @Converter
    public static class ProgrammingLanguageCodeConverter implements AttributeConverter<ProgrammingLanguageCode, String> {

        @Override
        public String convertToDatabaseColumn(ProgrammingLanguageCode attribute) {
            return attribute.getCode();
        }

        @Override
        public ProgrammingLanguageCode convertToEntityAttribute(String dbData) {
            return Optional.of(ProgrammingLanguageCode.find(dbData))
                    .orElseThrow(AttributeConvertFailedException::new);
        }
    }
}