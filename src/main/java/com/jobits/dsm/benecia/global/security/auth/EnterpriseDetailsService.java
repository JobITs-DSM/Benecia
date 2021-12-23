package com.jobits.dsm.benecia.global.security.auth;

import com.jobits.dsm.benecia.domain.enterprise.domain.EnterpriseRepository;
import com.jobits.dsm.benecia.global.security.exceptions.SecurityEnterpriseNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnterpriseDetailsService implements UserDetailsService {

    private final EnterpriseRepository enterpriseRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return enterpriseRepository.findById(username)
                .map(AuthDetails::new)
                .orElseThrow(() -> SecurityEnterpriseNotFoundException.EXCEPTION);
    }
}
