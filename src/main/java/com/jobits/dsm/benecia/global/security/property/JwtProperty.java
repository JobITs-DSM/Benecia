package com.jobits.dsm.benecia.global.security.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "auth.jwt")
public class JwtProperty {
    private final String secret;
    private final JwtExpProperty exp;

    @Getter
    @AllArgsConstructor
    public static class JwtExpProperty {
        private final Long access;
        private final Long refresh;
    }
}
