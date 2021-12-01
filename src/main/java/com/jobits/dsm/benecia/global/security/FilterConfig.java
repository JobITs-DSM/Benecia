package com.jobits.dsm.benecia.global.security;

import com.jobits.dsm.benecia.global.error.handler.ExceptionHandlerFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Override
    public void configure(HttpSecurity builder) {
        ExceptionHandlerFilter exceptionFilter = new ExceptionHandlerFilter();
        builder.addFilterBefore(exceptionFilter, UsernamePasswordAuthenticationFilter.class);
        // TODO: 2021/11/29 SecurityFilter 를 만들면 exceptionFilter - SecurityFilter - UsernamePasswordAuthenticationFilter 순으로 적용
    }
}
