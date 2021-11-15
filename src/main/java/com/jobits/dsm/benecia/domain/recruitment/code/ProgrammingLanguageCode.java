package com.jobits.dsm.benecia.domain.recruitment.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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

}