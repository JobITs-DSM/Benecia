package com.jobits.dsm.benecia.domain.admin.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "auth.admin")
public class AdminProperty {

    private final String id;
    private final String password;
}
