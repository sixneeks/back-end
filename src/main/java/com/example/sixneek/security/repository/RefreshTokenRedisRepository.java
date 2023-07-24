package com.example.sixneek.security.repository;

import com.example.sixneek.security.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, Long> {

    RefreshToken findByRefreshToken(String refreshToken);
}