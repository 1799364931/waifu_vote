package com.example.galgame_vote.pojo.voting;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TokenStorage {
    //存储
    private final RedisTemplate<String, String> redisTemplate;

    public TokenStorage(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void storeToken(String account, String token) {
        redisTemplate.opsForValue().set(account, token,1, TimeUnit.DAYS);
    }

    public String getToken(String account) {
        return redisTemplate.opsForValue().get(account);
    }

    public void deleteAccount(String account) {
        redisTemplate.delete(account);
    }

    public boolean containsAccount(String account) {
        return redisTemplate.hasKey(account);
    }



}
