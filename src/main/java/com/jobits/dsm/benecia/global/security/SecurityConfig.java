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
import org.springframework.web.cors.CorsUtils;

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
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(HttpMethod.GET, "/enterprise/codes/**").permitAll()
                .antMatchers(HttpMethod.GET, "/recruitment/codes/**").permitAll()
                .antMatchers(HttpMethod.GET, "/student/codes/**").permitAll()
                .antMatchers(HttpMethod.POST, "/student/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/admin/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/enterprise/auth").permitAll()
                .antMatchers(HttpMethod.PUT, "/v1/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/recruitment").permitAll()
                .antMatchers(HttpMethod.GET, "/recruitment/current").permitAll()
                .antMatchers(HttpMethod.GET, "/region").permitAll()
                .antMatchers(HttpMethod.GET, "/tag").permitAll()
                .antMatchers(HttpMethod.GET, "/recruitment/{hiring-id}").permitAll()
                .antMatchers(HttpMethod.POST, "/student/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/recruitment/all").permitAll()
                .antMatchers(HttpMethod.POST, "/admin/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/enterprise/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/enterprise/leading").permitAll()
                .antMatchers(HttpMethod.GET, "/student/employment").permitAll()
                .antMatchers(HttpMethod.GET, "/student/department").permitAll()
                .antMatchers(HttpMethod.GET, "/student/department/{department-id}").permitAll()
                .antMatchers(HttpMethod.PATCH, "/student/{student-id}/status").permitAll()
                .antMatchers(HttpMethod.DELETE, "/student/{student-id}/status").permitAll()
                .antMatchers(HttpMethod.GET, "/review/{registration-number}").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new FilterConfig(jwtTokenProvider));

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
