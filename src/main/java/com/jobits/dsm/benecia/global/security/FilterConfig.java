package com.jobits.dsm.benecia.global.security;

import com.jobits.dsm.benecia.global.error.handler.ExceptionHandlerFilter;
import com.jobits.dsm.benecia.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void configure(HttpSecurity builder) {
        TokenFilter tokenFilter = new TokenFilter(jwtTokenProvider);
        ExceptionHandlerFilter exceptionFilter = new ExceptionHandlerFilter();
        builder.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        builder.addFilterBefore(exceptionFilter, TokenFilter.class);
    }
}
