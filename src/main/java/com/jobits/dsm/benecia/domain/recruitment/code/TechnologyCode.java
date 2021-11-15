package com.jobits.dsm.benecia.domain.recruitment.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TechnologyCode {

    REACT("TCH001", "React"),
    ANGULAR_JS("TCH002", "AngularJS"),
    SPRING("TCH003", "Spring"),
    DJANGO("TCH004", "DJango"),
    EXPRESS("TCH005", "Express"),
    AWS("TCH006", "AWS"),
    GIT("TCH007", "git"),
    GITHUB("TCH008", "Github"),
    NGINX("TCH009", "nginx"),
    APACHE("TCH010", "Apache"),
    FLASK("TCH010", "Flask"),
    FAST_API("TCH010", "FastAPI"),
    VUE_JS("TCH010", "Vue.js"),
    NEST_JS("TCH010", "Nest.js"),
    DOCKER("TCH010", "Docker"),
    LINUX("TCH010", "Linux");

    private final String code;
    private final String value;

}
