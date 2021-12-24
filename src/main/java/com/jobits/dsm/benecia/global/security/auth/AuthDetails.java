package com.jobits.dsm.benecia.global.security.auth;

import com.jobits.dsm.benecia.domain.student.domain.Student;
import com.jobits.dsm.benecia.global.security.property.JwtRoleProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class AuthDetails implements UserDetails {

    private final UserMarker userMarker;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role;
        if (userMarker instanceof Student) {
            role = JwtRoleProperty.STUDENT_ROLE;
        } else {
            role = JwtRoleProperty.ENTERPRISE_ROLE;
        }
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return userMarker.getPassword();
    }

    @Override
    public String getUsername() {
        return userMarker.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
