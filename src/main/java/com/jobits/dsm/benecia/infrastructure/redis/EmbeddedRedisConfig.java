package com.jobits.dsm.benecia.infrastructure.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Profile({"local", "test"})
@Configuration
public class EmbeddedRedisConfig {

    private static final RedisServer redisServer = new RedisServer(6379);

    @PostConstruct
    void setup() {
        redisServer.start();
    }

    @PreDestroy
    void stopRedis() {
        redisServer.stop();
    }

}
