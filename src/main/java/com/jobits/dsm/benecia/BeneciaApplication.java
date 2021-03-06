package com.jobits.dsm.benecia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ConfigurationPropertiesScan
public class BeneciaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeneciaApplication.class, args);
    }

}
