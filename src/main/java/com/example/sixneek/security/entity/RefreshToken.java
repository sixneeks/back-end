package com.example.sixneek.security.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "refresh", timeToLive = 10)
public class RefreshToken {
    @Id
    private String id; // email

    @Indexed
    private String refreshToken;
}