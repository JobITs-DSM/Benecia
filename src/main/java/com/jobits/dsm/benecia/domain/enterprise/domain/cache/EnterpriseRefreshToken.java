package com.jobits.dsm.benecia.domain.enterprise.domain.cache;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@RedisHash
public class EnterpriseRefreshToken {

    @Id
    private String registrationNumber;

    private String refreshToken;

    @TimeToLive
    private Long refreshExp;

}
