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
    APACHE("TCH011", "Apache"),
    FLASK("TCH012", "Flask"),
    FAST_API("TCH013", "FastAPI"),
    VUE_JS("TCH014", "Vue.js"),
    NEST_JS("TCH015", "Nest.js"),
    DOCKER("TCH016", "Docker"),
    LINUX("TCH017", "Linux");

    private final String code;
    private final String value;

}
