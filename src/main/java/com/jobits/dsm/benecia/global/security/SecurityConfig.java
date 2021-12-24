package com.jobits.dsm.benecia.global.security;

import com.jobits.dsm.benecia.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement().disable()
                .formLogin().disable()

                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/v1/student/auth").permitAll()
                    .antMatchers(HttpMethod.POST, "/v1/admin/auth").permitAll()
                    .antMatchers(HttpMethod.POST, "/v1/enterprise/auth").permitAll()
                    .antMatchers(HttpMethod.PUT, "/v1/auth").permitAll()
                    .anyRequest().authenticated()

                .and()
                .apply(new FilterConfig(jwtTokenProvider));

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
