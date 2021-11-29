package com.jobits.dsm.benecia.domain.recruitment.code;

import com.jobits.dsm.benecia.global.error.exception.AttributeConvertFailedException;
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
    FLASK("TCH011", "Flask"),
    FAST_API("TCH012", "FastAPI"),
    VUE_JS("TCH013", "Vue.js"),
    NEST_JS("TCH014", "Nest.js"),
    DOCKER("TCH015", "Docker"),
    LINUX("TCH016", "Linux");

    private final String code;
    private final String value;

    private static final Map<String, TechnologyCode> map =
            Collections.unmodifiableMap(Arrays.stream(TechnologyCode.values())
                    .collect(Collectors.toMap(TechnologyCode::getCode, Function.identity())));

    public static TechnologyCode find(String dbData) {
        return Optional.of(map.get(dbData))
                .orElseThrow(AttributeConvertFailedException::new);
    }

    @Converter
    public static class TechnologyCodeConverter implements AttributeConverter<TechnologyCode, String> {

        @Override
        public String convertToDatabaseColumn(TechnologyCode attribute) {
            return attribute.getCode();
        }

        @Override
        public TechnologyCode convertToEntityAttribute(String dbData) {
            return Optional.of(TechnologyCode.find(dbData))
                    .orElseThrow(AttributeConvertFailedException::new);
        }
    }
}
