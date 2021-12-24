package com.jobits.dsm.benecia.global.security.jwt;

import com.jobits.dsm.benecia.global.security.auth.EnterpriseDetailsService;
import com.jobits.dsm.benecia.global.security.auth.StudentDetailsService;
import com.jobits.dsm.benecia.global.security.dto.Tokens;
import com.jobits.dsm.benecia.global.security.exceptions.JwtInvalidSignatureException;
import com.jobits.dsm.benecia.global.security.exceptions.JwtTokenExpiredException;
import com.jobits.dsm.benecia.global.security.property.JwtProperty;
import com.jobits.dsm.benecia.global.security.property.JwtRoleProperty;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private static final String ACCESS_KEY = "access";
    private static final String REFRESH_KEY = "refresh";
    private static final String TOKEN_TYPE_HEADER = "typ";
    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String AUTHORITY_KEY = "aut";

    private final StudentDetailsService studentDetailsService;
    private final EnterpriseDetailsService enterpriseDetailsService;
    private final JwtProperty jwtProperty;

    public Tokens getToken(String subject, String role) {
        String accessToken = getToken(subject, role, ACCESS_KEY, jwtProperty.getExp().getAccess());
        String refreshToken = getToken(subject, role, REFRESH_KEY, jwtProperty.getExp().getRefresh());

        return Tokens.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String getBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.replace(TOKEN_PREFIX, "");
        }
        return null;
    }

    public Authentication authenticateUser(String token) {
        Claims claims = getClaims(token);
        UserDetails userDetails = getUserDetails(claims);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private UserDetails getUserDetails(Claims claims) {
        String subject = claims.getSubject();
        String role = claims.get(AUTHORITY_KEY).toString();

        if (role.equals(JwtRoleProperty.STUDENT_ROLE)) {
            return studentDetailsService.loadUserByUsername(subject);
        } else {
            return enterpriseDetailsService.loadUserByUsername(subject);
        }

    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperty.getSecretKey())
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw JwtTokenExpiredException.EXCEPTION;
        } catch (SignatureException e) {
            throw JwtInvalidSignatureException.EXCEPTION;
        }
    }

    private String getToken(String subject, String access, String type, Long exp) {
        return Jwts.builder()
                .setClaims(Map.of(AUTHORITY_KEY, access))
                .setIssuedAt(new Date())
                .setHeaderParam(TOKEN_TYPE_HEADER, type)
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS512, jwtProperty.getSecretKey())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }
}
